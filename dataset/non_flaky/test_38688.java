class DummyClass_38688 {
    @Test
    public void testTopicToIndexName() throws IOException {
        try (ElasticSearchClient client = new ElasticSearchClient(new ElasticSearchConfig()
                .setElasticSearchUrl("http://" + container.getHttpHostAddress())); ) {
            assertEquals(client.topicToIndexName("data-ks1.table1"), "data-ks1.table1");
            assertEquals(client.topicToIndexName("persistent://public/default/testesjson"), "testesjson");
            assertEquals(client.topicToIndexName("default/testesjson"), "testesjson");
            assertEquals(client.topicToIndexName(".testesjson"), ".testesjson");
            assertEquals(client.topicToIndexName("TEST"), "test");

            assertThrows(RuntimeException.class, () -> client.topicToIndexName("toto\\titi"));
            assertThrows(RuntimeException.class, () -> client.topicToIndexName("_abc"));
            assertThrows(RuntimeException.class, () -> client.topicToIndexName("-abc"));
            assertThrows(RuntimeException.class, () -> client.topicToIndexName("+abc"));
        }
    }

}