class DummyClass_38664 {
    @Test
    public void shouldIgnoreUnparseableMessages() throws Exception {
        try (final Producer<String> producer = pulsarClient.newProducer(Schema.STRING)
            .topic(topicName)
            .create()
        ) {
            producer.send("");
            producer.send("{\"position\":{\"filename\":\"my-txn-file.log\",\"position\":39},\"databaseName\":\"db1\",\"ddl\":\"DROP TABLE foo;\"}");
            producer.send("{\"source\":{\"server\":\"my-server\"},\"databaseName\":\"db1\",\"ddl\":\"DROP TABLE foo;\"}");
            producer.send("{\"source\":{\"server\":\"my-server\"},\"position\":{\"filename\":\"my-txn-file.log\",\"position\":39},\"databaseName\":\"db1\",\"ddl\":\"DROP TABLE foo;\"");
            producer.send("\"source\":{\"server\":\"my-server\"},\"position\":{\"filename\":\"my-txn-file.log\",\"position\":39},\"databaseName\":\"db1\",\"ddl\":\"DROP TABLE foo;\"}");
            producer.send("{\"source\":{\"server\":\"my-server\"},\"position\":{\"filename\":\"my-txn-file.log\",\"position\":39},\"databaseName\":\"db1\",\"ddl\":\"xxxDROP TABLE foo;\"}");
        }

        testHistoryTopicContent(true, true);
    }

}