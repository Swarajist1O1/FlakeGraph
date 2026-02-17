class DummyClass_38706 {
    @Test
    public void seekPauseResumeTest() throws Exception {
        KafkaConnectSink sink = new KafkaConnectSink();
        sink.open(props, context);

        final GenericRecord rec = getGenericRecord("value", Schema.STRING);
        Message msg = mock(MessageImpl.class);
        when(msg.getValue()).thenReturn(rec);
        final MessageId msgId = new MessageIdImpl(10, 10, 0);
        when(msg.getMessageId()).thenReturn(msgId);

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

        final TopicPartition tp = new TopicPartition("fake-topic", 0);
        assertNotEquals(MessageIdUtils.getOffset(msgId), 0);
        assertEquals(sink.currentOffset(tp.topic(), tp.partition()), MessageIdUtils.getOffset(msgId));

        sink.taskContext.offset(tp, 0);
        verify(context, times(1)).seek(Mockito.anyString(), Mockito.anyInt(), any());
        assertEquals(sink.currentOffset(tp.topic(), tp.partition()), 0);

        sink.taskContext.pause(tp);
        verify(context, times(1)).pause(tp.topic(), tp.partition());
        sink.taskContext.resume(tp);
        verify(context, times(1)).resume(tp.topic(), tp.partition());

        sink.close();
    }

}