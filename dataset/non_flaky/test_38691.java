class DummyClass_38691 {
    @Test
    public void testBulkRetry() throws Exception {
        final String index = "indexbulktest-" + UUID.randomUUID();
        ElasticSearchConfig config = new ElasticSearchConfig()
                .setElasticSearchUrl("http://"+container.getHttpHostAddress())
                .setIndexName(index)
                .setBulkEnabled(true)
                .setMaxRetries(1000)
                .setBulkActions(2)
                .setRetryBackoffInMs(100)
                // disabled, we want to have full control over flush() method
                .setBulkFlushIntervalInMs(-1);

        try (ElasticSearchClient client = new ElasticSearchClient(config);) {
            try {
                assertTrue(client.createIndexIfNeeded(index));
                MockRecord<GenericObject> mockRecord = new MockRecord<>();
                client.bulkIndex(mockRecord, Pair.of("1", "{\"a\":1}"));
                client.bulkIndex(mockRecord, Pair.of("2", "{\"a\":2}"));
                assertEquals(mockRecord.acked, 2);
                assertEquals(mockRecord.failed, 0);
                assertEquals(client.totalHits(index), 2);

                ChaosContainer<?> chaosContainer = new ChaosContainer<>(container.getContainerName(), "15s");
                chaosContainer.start();

                client.bulkIndex(mockRecord, Pair.of("3", "{\"a\":3}"));
                assertEquals(mockRecord.acked, 2);
                assertEquals(mockRecord.failed, 0);
                assertEquals(client.totalHits(index), 2);

                chaosContainer.stop();
                client.flush();
                assertEquals(mockRecord.acked, 3);
                assertEquals(mockRecord.failed, 0);
                assertEquals(client.totalHits(index), 3);
            } finally {
                client.delete(index);
            }
        }
    }

}