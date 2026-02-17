class DummyClass_38705 {
    @Test
    public void smokeTest() throws Exception {
        KafkaConnectSink sink = new KafkaConnectSink();
        sink.open(props, context);

        final GenericRecord rec = getGenericRecord("value", Schema.STRING);
        Message msg = mock(MessageImpl.class);
        when(msg.getValue()).thenReturn(rec);
        when(msg.getMessageId()).thenReturn(new MessageIdImpl(1, 0, 0));

        final AtomicInteger status = new AtomicInteger(0);
        Record<GenericObject> record = PulsarRecord.<String>builder()
                .topicName("fake-topic")
                .message(msg)
                .ackFunction(status::incrementAndGet)
                .failFunction(status::decrementAndGet)
                .schema(Schema.STRING)
                .build();

        sink.write(record);
        sink.flush();

        assertEquals(status.get(), 1);

        sink.close();

        List<String> lines = Files.readAllLines(file, StandardCharsets.US_ASCII);
        assertEquals(lines.get(0), "value");
    }

}