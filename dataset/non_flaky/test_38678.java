class DummyClass_38678 {
    @Test
    public void TestOpenAndWriteSink() {
        Map<String, Object> configs = new HashMap<>();
        configs.put("host", "localhost");
        configs.put("port", "5672");
        configs.put("virtualHost", "default");
        configs.put("username", "guest");
        configs.put("password", "guest");
        configs.put("queueName", "test-queue");
        configs.put("connectionName", "test-connection");
        configs.put("requestedChannelMax", "0");
        configs.put("requestedFrameMax", "0");
        configs.put("connectionTimeout", "60000");
        configs.put("handshakeTimeout", "10000");
        configs.put("requestedHeartbeat", "60");
        configs.put("prefetchCount", "0");
        configs.put("prefetchGlobal", "false");
        configs.put("passive", "false");

        RabbitMQSource source = new RabbitMQSource();

        // open should success
        // rabbitmq service may need time to initialize
        Awaitility.await().ignoreExceptions().untilAsserted(() -> source.open(configs, null));
    }

}