class DummyClass_89333 {
  @Test
  public void testCreateStream() {
    StreamSpec spec = new StreamSpec("testId", "testStream", "testSystem", 8);
    KafkaSystemAdmin admin = systemAdmin();
    assertTrue("createStream should return true if the stream does not exist and then is created.",
        admin.createStream(spec));
    admin.validateStream(spec);

    assertFalse("createStream should return false if the stream already exists.", systemAdmin().createStream(spec));
  }

}