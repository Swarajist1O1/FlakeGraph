class DummyClass_35671 {
  @Test (expected = IOException.class)
  public void testWritesOnClosedWriter() throws IOException {
    LogBufferWriter writer = new LogBufferWriter(TMP_FOLDER.newFolder().getAbsolutePath(), 100000, () -> { });
    writer.close();
    // should throw IOException
    writer.write(ImmutableList.of(
      serializer.toBytes(createLoggingEvent("test.logger", Level.INFO, "0", 1,
                                            new WorkerLoggingContext("default", "app1", "worker1", "run1",
                                                                     "instance1")))).iterator());
  }

}