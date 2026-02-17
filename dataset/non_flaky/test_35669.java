class DummyClass_35669 {
  @Test
  public void testLogBufferWriter() throws Exception {
    String absolutePath = TMP_FOLDER.newFolder().getAbsolutePath();

    LogBufferWriter writer = new LogBufferWriter(absolutePath, 100000, () -> { });
    ImmutableList<byte[]> events = getLoggingEvents();
    Iterator<LogBufferEvent> writtenEvents = writer.write(events.iterator()).iterator();
    writer.close();

    int i = 0;
    int startPos = 0;
    // verify if correct offsets were set without file rotation
    while (writtenEvents.hasNext()) {
      LogBufferEvent bufferEvent = writtenEvents.next();
      Assert.assertEquals(String.valueOf(i++), bufferEvent.getLogEvent().getMessage());
      // There will not be any rotation.
      Assert.assertEquals(bufferEvent.getOffset().getFilePos(), startPos);
      startPos = startPos + Bytes.SIZEOF_INT + serializer.toBytes(bufferEvent.getLogEvent()).length;
    }

    // verify if the events were serialized and written correctly
    try (DataInputStream dis = new DataInputStream(new FileInputStream(absolutePath + "/0.buf"))) {
      for (byte[] eventBytes : events) {
        ILoggingEvent event = serializer.fromBytes(ByteBuffer.wrap(eventBytes));
        Assert.assertEquals(event.getMessage(), getEvent(dis, serializer.toBytes(event).length).getMessage());
      }
    }
  }

}