class DummyClass_89335 {
  @Test(expected = StreamValidationException.class)
  public void testValidateStreamWrongPartitionCount() {
    StreamSpec spec1 = new StreamSpec("testId", "testStreamPartition", "testSystem", 8);
    StreamSpec spec2 = new StreamSpec("testId", "testStreamPartition", "testSystem", 4);

    assertTrue("createStream should return true if the stream does not exist and then is created.",
        systemAdmin().createStream(spec1));

    systemAdmin().validateStream(spec2);
  }

}