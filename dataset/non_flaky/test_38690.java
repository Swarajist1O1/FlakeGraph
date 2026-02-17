class DummyClass_38690 {
    @Test
    public void testMalformedDocIgnore() throws Exception {
        String index = "indexmalformed2-" + UUID.randomUUID();
        ElasticSearchConfig config = new ElasticSearchConfig()
                .setElasticSearchUrl("http://"+container.getHttpHostAddress())
                .setIndexName(index)
                .setBulkEnabled(true)
                .setMalformedDocAction(ElasticSearchConfig.MalformedDocAction.IGNORE);
        try (ElasticSearchClient client = new ElasticSearchClient(config);) {
            MockRecord<GenericObject> mockRecord = new MockRecord<>();
            client.bulkIndex(mockRecord, Pair.of("1", "{\"a\":1}"));
            client.bulkIndex(mockRecord, Pair.of("2", "{\"a\":\"toto\"}"));
            client.flush();
            assertNull(client.irrecoverableError.get());
            assertEquals(mockRecord.acked, 1);
            assertEquals(mockRecord.failed, 1);
        }
    }

}