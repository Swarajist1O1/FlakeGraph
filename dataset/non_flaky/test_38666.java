class DummyClass_38666 {
    @Test
    public void testExists() throws Exception {
        // happy path
        testHistoryTopicContent(true, false);
        assertTrue(history.exists());

        // Set history to use dummy topic
        Configuration config = Configuration.create()
            .with(PulsarDatabaseHistory.SERVICE_URL, brokerUrl.toString())
            .with(PulsarDatabaseHistory.TOPIC, "persistent://my-property/my-ns/dummytopic")
            .with(DatabaseHistory.NAME, "my-db-history")
            .with(DatabaseHistory.SKIP_UNPARSEABLE_DDL_STATEMENTS, true)
            .build();

        history.configure(config, null, DatabaseHistoryListener.NOOP, true);
        history.start();

        // dummytopic should not exist yet
        assertFalse(history.exists());
    }

}