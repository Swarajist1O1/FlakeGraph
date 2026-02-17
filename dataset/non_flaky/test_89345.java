class DummyClass_89345 {
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidPartitionCount() {
    new KafkaStreamSpec("dummyId", "dummyPhysicalName", "dummySystemName", 0);
  }

}