class DummyClass_38692 {
    @Test
    public void testBulkBlocking() throws Exception {
        final String index = "indexblocking-" + UUID.randomUUID();
        ElasticSearchConfig config = new ElasticSearchConfig()
                .setElasticSearchUrl("http://"+container.getHttpHostAddress())
                .setIndexName(index)
                .setBulkEnabled(true)
                .setMaxRetries(1000)
                .setBulkActions(2)
                .setBulkConcurrentRequests(2)
                .setRetryBackoffInMs(100)
                .setBulkFlushIntervalInMs(10000);
        try (ElasticSearchClient client = new ElasticSearchClient(config);) {
            assertTrue(client.createIndexIfNeeded(index));

            try {
                MockRecord<GenericObject> mockRecord = new MockRecord<>();
                for (int i = 1; i <= 5; i++) {
                    client.bulkIndex(mockRecord, Pair.of(Integer.toString(i), "{\"a\":" + i + "}"));
                }

                Awaitility.await().untilAsserted(() -> {
                    assertThat("acked record", mockRecord.acked, greaterThanOrEqualTo(4));
                    assertEquals(mockRecord.failed, 0);
                    assertThat("totalHits", client.totalHits(index), greaterThanOrEqualTo(4L));
                });
                client.flush();
                Awaitility.await().untilAsserted(() -> {
                    assertEquals(mockRecord.acked, 5);
                    assertEquals(mockRecord.failed, 0);
                    assertEquals(client.totalHits(index), 5);
                });

                ChaosContainer<?> chaosContainer = new ChaosContainer<>(container.getContainerName(), "30s");
                chaosContainer.start();
                Thread.sleep(1000L);

                // 11th bulkIndex is blocking because we have 2 pending requests, and the 3rd request is blocked.
                long start = System.currentTimeMillis();
                for (int i = 6; i <= 15; i++) {
                    client.bulkIndex(mockRecord, Pair.of(Integer.toString(i), "{\"a\":" + i + "}"));
                    log.info("{} index {}", System.currentTimeMillis(), i);
                }
                long elapsed = System.currentTimeMillis() - start;
                log.info("elapsed = {}", elapsed);
                assertTrue(elapsed > 29000); // bulkIndex was blocking while elasticsearch was down or busy

                Thread.sleep(1000L);
                assertEquals(mockRecord.acked, 15);
                assertEquals(mockRecord.failed, 0);
                assertEquals(client.records.size(), 0);

                chaosContainer.stop();
            } finally {
                client.delete(index);
            }
        }
    }

}