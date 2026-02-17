class DummyClass_38677 {
    @Test
    public void TestOpenAndWriteSink() throws Exception {
        Map<String, Object> configs = new HashMap<>();
        configs.put("host", "localhost");
        configs.put("port", "5673");
        configs.put("virtualHost", "default");
        configs.put("username", "guest");
        configs.put("password", "guest");
        configs.put("connectionName", "test-connection");
        configs.put("requestedChannelMax", "0");
        configs.put("requestedFrameMax", "0");
        configs.put("connectionTimeout", "60000");
        configs.put("handshakeTimeout", "10000");
        configs.put("requestedHeartbeat", "60");
        configs.put("exchangeName", "test-exchange");
        configs.put("exchangeType", "fanout");

        RabbitMQSink sink = new RabbitMQSink();

        // open should success
        // rabbitmq service may need time to initialize
        Awaitility.await().ignoreExceptions().untilAsserted(() -> sink.open(configs, null));

        // write should success
        Record<byte[]> record = build("test-topic", "fakeKey", "fakeValue", "fakeRoutingKey");
        sink.write(record);

        sink.close();
    }

}