class DummyClass_89322 {
  @Test
  public void testFetchThresholdBytes() {

    SystemStreamPartition ssp0 = new SystemStreamPartition(TEST_SYSTEM, TEST_STREAM, new Partition(0));
    SystemStreamPartition ssp1 = new SystemStreamPartition(TEST_SYSTEM, TEST_STREAM, new Partition(1));
    int partitionsNum = 2;
    int ime0Size = Integer.valueOf(FETCH_THRESHOLD_MSGS) / partitionsNum; // fake size
    int ime1Size = Integer.valueOf(FETCH_THRESHOLD_MSGS) / partitionsNum - 1; // fake size
    int ime11Size = 20;
    ByteArraySerializer bytesSerde = new ByteArraySerializer();
    IncomingMessageEnvelope ime0 = new IncomingMessageEnvelope(ssp0, "0", bytesSerde.serialize("", "key0".getBytes()),
        bytesSerde.serialize("", "value0".getBytes()), ime0Size);
    IncomingMessageEnvelope ime1 = new IncomingMessageEnvelope(ssp1, "0", bytesSerde.serialize("", "key1".getBytes()),
        bytesSerde.serialize("", "value1".getBytes()), ime1Size);
    IncomingMessageEnvelope ime11 = new IncomingMessageEnvelope(ssp1, "0", bytesSerde.serialize("", "key11".getBytes()),
        bytesSerde.serialize("", "value11".getBytes()), ime11Size);
    KafkaSystemConsumer consumer = createConsumer(FETCH_THRESHOLD_MSGS, FETCH_THRESHOLD_BYTES);

    consumer.register(ssp0, "0");
    consumer.register(ssp1, "0");
    consumer.start();
    consumer.messageSink.addMessage(ssp0, ime0);
    // queue for ssp0 should be full now, because we added message of size FETCH_THRESHOLD_MSGS/partitionsNum
    Assert.assertFalse(consumer.messageSink.needsMoreMessages(ssp0));
    consumer.messageSink.addMessage(ssp1, ime1);
    // queue for ssp1 should be less then full now, because we added message of size (FETCH_THRESHOLD_MSGS/partitionsNum - 1)
    Assert.assertTrue(consumer.messageSink.needsMoreMessages(ssp1));
    consumer.messageSink.addMessage(ssp1, ime11);
    // queue for ssp1 should full now, because we added message of size 20 on top
    Assert.assertFalse(consumer.messageSink.needsMoreMessages(ssp1));

    Assert.assertEquals(1, consumer.getNumMessagesInQueue(ssp0));
    Assert.assertEquals(2, consumer.getNumMessagesInQueue(ssp1));
    Assert.assertEquals(ime0Size, consumer.getMessagesSizeInQueue(ssp0));
    Assert.assertEquals(ime1Size + ime11Size, consumer.getMessagesSizeInQueue(ssp1));

    consumer.stop();
  }

}