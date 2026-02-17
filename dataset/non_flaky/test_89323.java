class DummyClass_89323 {
  @Test
  public void testFetchThresholdBytesDiabled() {
    // Pass 0 as fetchThresholdByBytes, which disables checking for limit by size

    SystemStreamPartition ssp0 = new SystemStreamPartition(TEST_SYSTEM, TEST_STREAM, new Partition(0));
    SystemStreamPartition ssp1 = new SystemStreamPartition(TEST_SYSTEM, TEST_STREAM, new Partition(1));
    int partitionsNum = 2;
    int ime0Size = Integer.valueOf(FETCH_THRESHOLD_MSGS) / partitionsNum; // fake size, upto the limit
    int ime1Size = Integer.valueOf(FETCH_THRESHOLD_MSGS) / partitionsNum - 100; // fake size, below the limit
    int ime11Size = 20; // event with the second message still below the size limit
    ByteArraySerializer bytesSerde = new ByteArraySerializer();
    IncomingMessageEnvelope ime0 = new IncomingMessageEnvelope(ssp0, "0", bytesSerde.serialize("", "key0".getBytes()),
        bytesSerde.serialize("", "value0".getBytes()), ime0Size);
    IncomingMessageEnvelope ime1 = new IncomingMessageEnvelope(ssp1, "0", bytesSerde.serialize("", "key1".getBytes()),
        bytesSerde.serialize("", "value1".getBytes()), ime1Size);
    IncomingMessageEnvelope ime11 = new IncomingMessageEnvelope(ssp1, "0", bytesSerde.serialize("", "key11".getBytes()),
        bytesSerde.serialize("", "value11".getBytes()), ime11Size);

    // limit by number of messages 4/2 = 2 per partition
    // limit by number of bytes - disabled
    KafkaSystemConsumer consumer = createConsumer("4", "0"); // should disable

    consumer.register(ssp0, "0");
    consumer.register(ssp1, "0");
    consumer.start();
    consumer.messageSink.addMessage(ssp0, ime0);
    // should be full by size, but not full by number of messages (1 of 2)
    Assert.assertTrue(consumer.messageSink.needsMoreMessages(ssp0));
    consumer.messageSink.addMessage(ssp1, ime1);
    // not full neither by size nor by messages
    Assert.assertTrue(consumer.messageSink.needsMoreMessages(ssp1));
    consumer.messageSink.addMessage(ssp1, ime11);
    // not full by size, but should be full by messages
    Assert.assertFalse(consumer.messageSink.needsMoreMessages(ssp1));

    Assert.assertEquals(1, consumer.getNumMessagesInQueue(ssp0));
    Assert.assertEquals(2, consumer.getNumMessagesInQueue(ssp1));
    Assert.assertEquals(ime0Size, consumer.getMessagesSizeInQueue(ssp0));
    Assert.assertEquals(ime1Size + ime11Size, consumer.getMessagesSizeInQueue(ssp1));

    consumer.stop();
  }

}