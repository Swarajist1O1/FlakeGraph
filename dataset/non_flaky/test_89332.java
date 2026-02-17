class DummyClass_89332 {
  @Test
  public void testCreateChangelogStreamWithSpecialCharsInTopicName() {
    // cannot contain period
    testCreateChangelogStreamHelp("test-Change_Log-Stream");
  }

}