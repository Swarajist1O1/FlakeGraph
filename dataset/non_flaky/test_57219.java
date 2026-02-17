class DummyClass_57219 {
  @Test
  public void testReprocess() {
    OMMetadataManager omMetadataManager = mock(OmMetadataManagerImpl.class);
    // Mock 5 rows in each table and test the count
    for (String tableName: tableCountTask.getTaskTables()) {
      TypedTable<String, Object> table = mock(TypedTable.class);
      TypedTable.TypedTableIterator mockIter = mock(TypedTable
          .TypedTableIterator.class);
      when(table.iterator()).thenReturn(mockIter);
      when(omMetadataManager.getTable(tableName)).thenReturn(table);
      when(mockIter.hasNext())
          .thenReturn(true)
          .thenReturn(true)
          .thenReturn(true)
          .thenReturn(true)
          .thenReturn(true)
          .thenReturn(false);
    }

    Pair<String, Boolean> result = tableCountTask.reprocess(omMetadataManager);
    assertTrue(result.getRight());

    assertEquals(5L, getCountForTable(KEY_TABLE));
    assertEquals(5L, getCountForTable(VOLUME_TABLE));
    assertEquals(5L, getCountForTable(BUCKET_TABLE));
    assertEquals(5L, getCountForTable(OPEN_KEY_TABLE));
    assertEquals(5L, getCountForTable(DELETED_TABLE));
  }

}