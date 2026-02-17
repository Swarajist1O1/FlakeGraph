class DummyClass_57220 {
  @Test
  public void testProcess() {
    ArrayList<OMDBUpdateEvent> events = new ArrayList<>();
    // Create 5 put, 1 delete and 1 update event for each table
    for (String tableName: tableCountTask.getTaskTables()) {
      for (int i=0; i<5; i++) {
        events.add(getOMUpdateEvent("item" + i, null, tableName, PUT));
      }
      // for delete event, if value is set to null, the counter will not be
      // decremented. This is because the value will be null if item does not
      // exist in the database and there is no need to delete.
      events.add(getOMUpdateEvent("item0", mock(OmKeyInfo.class), tableName,
          DELETE));
      events.add(getOMUpdateEvent("item1", null, tableName, UPDATE));
    }
    OMUpdateEventBatch omUpdateEventBatch = new OMUpdateEventBatch(events);
    tableCountTask.process(omUpdateEventBatch);

    // Verify 4 items in each table. (5 puts - 1 delete + 0 update)
    assertEquals(4L, getCountForTable(KEY_TABLE));
    assertEquals(4L, getCountForTable(VOLUME_TABLE));
    assertEquals(4L, getCountForTable(BUCKET_TABLE));
    assertEquals(4L, getCountForTable(OPEN_KEY_TABLE));
    assertEquals(4L, getCountForTable(DELETED_TABLE));

    // add a new key and simulate delete on non-existing item (value: null)
    ArrayList<OMDBUpdateEvent> newEvents = new ArrayList<>();
    for (String tableName: tableCountTask.getTaskTables()) {
      newEvents.add(getOMUpdateEvent("item5", null, tableName, PUT));
      // This delete event should be a noop since value is null
      newEvents.add(getOMUpdateEvent("item0", null, tableName, DELETE));
    }

    omUpdateEventBatch = new OMUpdateEventBatch(newEvents);
    tableCountTask.process(omUpdateEventBatch);

    // Verify 5 items in each table. (1 new put + 0 delete)
    assertEquals(5L, getCountForTable(KEY_TABLE));
    assertEquals(5L, getCountForTable(VOLUME_TABLE));
    assertEquals(5L, getCountForTable(BUCKET_TABLE));
    assertEquals(5L, getCountForTable(OPEN_KEY_TABLE));
    assertEquals(5L, getCountForTable(DELETED_TABLE));
  }

}