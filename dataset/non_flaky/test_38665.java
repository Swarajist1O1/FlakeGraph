class DummyClass_38665 {
    @Test(expectedExceptions = ParsingException.class)
    public void shouldStopOnUnparseableSQL() throws Exception {
        try (final Producer<String> producer = pulsarClient.newProducer(Schema.STRING).topic(topicName).create()) {
            producer.send("{\"source\":{\"server\":\"my-server\"},\"position\":{\"filename\":\"my-txn-file.log\",\"position\":39},\"databaseName\":\"db1\",\"ddl\":\"xxxDROP TABLE foo;\"}");
        }

        testHistoryTopicContent(false, false);
    }

}