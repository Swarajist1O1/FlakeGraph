class DummyClass_38687 {
    @Test
    public void testIndexExists() throws IOException {
        String index = "mynewindex-" + UUID.randomUUID();
        try (ElasticSearchClient client = new ElasticSearchClient(new ElasticSearchConfig()
                .setElasticSearchUrl("http://" + container.getHttpHostAddress())
                .setIndexName(index));) {
            assertFalse(client.indexExists(index));
            assertTrue(client.createIndexIfNeeded(index));
            try {
                assertTrue(client.indexExists(index));
                assertFalse(client.createIndexIfNeeded(index));
            } finally {
                client.delete(index);
            }
        }
    }

}