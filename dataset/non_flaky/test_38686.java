class DummyClass_38686 {
    @Test
    public void testIndexDelete() throws Exception {
        String index = "myindex-" + UUID.randomUUID();
        try (ElasticSearchClient client = new ElasticSearchClient(new ElasticSearchConfig()
                .setElasticSearchUrl("http://" + container.getHttpHostAddress())
                .setIndexName(index));) {
            assertTrue(client.createIndexIfNeeded(index));
            try {
                MockRecord<GenericObject> mockRecord = new MockRecord<>();
                client.indexDocument(mockRecord, Pair.of("1", "{ \"a\":1}"));
                assertEquals(mockRecord.acked, 1);
                assertEquals(mockRecord.failed, 0);
                assertEquals(client.totalHits(index), 1);

                client.deleteDocument(mockRecord, "1");
                assertEquals(mockRecord.acked, 2);
                assertEquals(mockRecord.failed, 0);
                assertEquals(client.totalHits(index), 0);
            } finally {
                client.delete(index);
            }
        }
    }

}