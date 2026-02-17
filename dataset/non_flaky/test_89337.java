class DummyClass_89337 {
  @Test
  public void testClearStream() {
    StreamSpec spec = new StreamSpec("testId", "testStreamClear", "testSystem", 8);

    KafkaSystemAdmin admin = systemAdmin();
    String topicName = spec.getPhysicalName();

    assertTrue("createStream should return true if the stream does not exist and then is created.", admin.createStream(spec));
    // validate topic exists
    assertTrue(admin.clearStream(spec));

    // validate that topic was removed
    DescribeTopicsResult dtr = admin.adminClient.describeTopics(ImmutableSet.of(topicName));
    try {
      TopicDescription td = dtr.all().get().get(topicName);
      Assert.fail("topic " + topicName + " should've been removed. td=" + td);
    } catch (Exception e) {
      if (!(e.getCause() instanceof org.apache.kafka.common.errors.UnknownTopicOrPartitionException)) {
        Assert.fail("topic " + topicName + " should've been removed. Expected UnknownTopicOrPartitionException.");
      }
    }
  }

}