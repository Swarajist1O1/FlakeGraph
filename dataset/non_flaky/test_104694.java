class DummyClass_104694 {
  @Test
  public void testConsumerDirectoryExists() {
    File consumerDirectory = new File(CONSUMER_DIRECTORY, "mytable_REALTIME");
    assertEquals(consumerDirectory.exists(), _isConsumerDirConfigured,
        "The off heap consumer directory does not exist");
  }

}