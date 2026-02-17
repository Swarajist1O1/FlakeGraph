class DummyClass_38689 {
    @Test
    public void testMalformedDocFails() throws Exception {
        String index = "indexmalformed-" + UUID.randomUUID();
        ElasticSearchConfig config = new ElasticSearchConfig()
                .setElasticSearchUrl("http://"+container.getHttpHostAddress())
                .setIndexName(index)
                .setBulkEnabled(true)
                .setMalformedDocAction(ElasticSearchConfig.MalformedDocAction.FAIL);
        try (ElasticSearchClient client = new ElasticSearchClient(config);) {
            MockRecord<GenericObject> mockRecord = new MockRecord<>();
            client.bulkIndex(mockRecord, Pair.of("1", "{\"a\":1}"));
            client.bulkIndex(mockRecord, Pair.of("2", "{\"a\":\"toto\"}"));
            client.flush();
            assertNotNull(client.irrecoverableError.get());
            assertTrue(client.irrecoverableError.get().getMessage().contains("mapper_parsing_exception"));
            assertEquals(mockRecord.acked, 1);
            assertEquals(mockRecord.failed, 1);
            assertThrows(Exception.class, () -> client.bulkIndex(mockRecord, Pair.of("3", "{\"a\":3}")));
            assertEquals(mockRecord.acked, 1);
            assertEquals(mockRecord.failed, 2);
        }
    }

}