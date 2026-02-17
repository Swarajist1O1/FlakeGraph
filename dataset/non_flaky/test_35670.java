class DummyClass_35670 {
  @Test
  public void testFileRotation() throws Exception {
    // Make sure rotation happens after every event is written
    LogBufferWriter writer = new LogBufferWriter(TMP_FOLDER.newFolder().getAbsolutePath(), 10, () -> { });
    ImmutableList<byte[]> events = getLoggingEvents();
    Iterator<LogBufferEvent> writtenEvents = writer.write(events.iterator()).iterator();
    writer.close();

    int i = 0;
    // verify if correct offsets and file id were set with file rotation
    while (writtenEvents.hasNext()) {
      LogBufferEvent bufferEvent = writtenEvents.next();
      Assert.assertEquals(bufferEvent.getLogEvent().getMessage(), String.valueOf(i));
      // There will be 2 events in one file.
      Assert.assertEquals(i++ + ".buf", bufferEvent.getOffset().getFileId() + ".buf");
      Assert.assertEquals(0, bufferEvent.getOffset().getFilePos());
    }
  }

}