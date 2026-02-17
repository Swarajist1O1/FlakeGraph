class DummyClass_35677 {
  @Test
  public void testLogReader() throws Exception {
    String absolutePath = TMP_FOLDER.newFolder().getAbsolutePath();

    LogBufferWriter writer = new LogBufferWriter(absolutePath, 250, () -> { });
    ImmutableList<byte[]> events = getLoggingEvents();
    Iterable<LogBufferEvent> writtenEvents = writer.write(events.iterator());
    writer.close();

    List<LogBufferEvent> logBufferEvents = new LinkedList<>();
    // read from start positions, tests case where no checkpoints are persisted
    LogBufferReader reader = new LogBufferReader(absolutePath, 2, 3, -1, -1);
    Iterator<LogBufferEvent> iterator = writtenEvents.iterator();
    verifyEvents(logBufferEvents, reader, iterator);
    reader.close();

    // this should skip first and second event, this is because log buffer offsets are offset for event that is
    // already stored. so in this case, skip first event and skip second event as second event is the last stored event
    reader = new LogBufferReader(absolutePath, 2, 3, 0, 145);
    iterator = writtenEvents.iterator();
    iterator.next();
    iterator.next();
    verifyEvents(logBufferEvents, reader, iterator);
    reader.close();
  }

}