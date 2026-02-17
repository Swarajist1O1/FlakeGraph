class DummyClass_38662 {
    @Test
    public void testInvalidConfigWillThrownException() throws Exception {
        KafkaAbstractSource source = new DummySource();
        SourceContext ctx = mock(SourceContext.class);
        Map<String, Object> config = new HashMap<>();
        Assert.ThrowingRunnable openAndClose = ()->{
            try {
                source.open(config, ctx);
                fail();
            } finally {
                source.close();
            }
        };
        expectThrows(NullPointerException.class, openAndClose);
        config.put("topic", "topic_1");
        expectThrows(NullPointerException.class, openAndClose);
        config.put("bootstrapServers", "localhost:8080");
        expectThrows(NullPointerException.class, openAndClose);
        config.put("groupId", "test-group");
        config.put("fetchMinBytes", -1);
        expectThrows(IllegalArgumentException.class, openAndClose);
        config.put("fetchMinBytes", 1000);
        config.put("autoCommitEnabled", true);
        config.put("autoCommitIntervalMs", -1);
        expectThrows(IllegalArgumentException.class, openAndClose);
        config.put("autoCommitIntervalMs", 100);
        config.put("sessionTimeoutMs", -1);
        expectThrows(IllegalArgumentException.class, openAndClose);
        config.put("sessionTimeoutMs", 10000);
        config.put("heartbeatIntervalMs", -100);
        expectThrows(IllegalArgumentException.class, openAndClose);
        config.put("heartbeatIntervalMs", 20000);
        expectThrows(IllegalArgumentException.class, openAndClose);
        config.put("heartbeatIntervalMs", 5000);
        config.put("autoOffsetReset", "some-value");
        expectThrows(IllegalArgumentException.class, openAndClose);
        config.put("autoOffsetReset", "earliest");
        source.open(config, ctx);
        source.close();
    }

}