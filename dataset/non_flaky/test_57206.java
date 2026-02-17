class DummyClass_57206 {
  @Test
  public void testReprocess() throws IOException {
    OmKeyInfo omKeyInfo1 = mock(OmKeyInfo.class);
    given(omKeyInfo1.getKeyName()).willReturn("key1");
    given(omKeyInfo1.getVolumeName()).willReturn("vol1");
    given(omKeyInfo1.getBucketName()).willReturn("bucket1");
    given(omKeyInfo1.getDataSize()).willReturn(1000L);

    OmKeyInfo omKeyInfo2 = mock(OmKeyInfo.class);
    given(omKeyInfo2.getKeyName()).willReturn("key2");
    given(omKeyInfo2.getVolumeName()).willReturn("vol1");
    given(omKeyInfo2.getBucketName()).willReturn("bucket1");
    given(omKeyInfo2.getDataSize()).willReturn(100000L);

    OmKeyInfo omKeyInfo3 = mock(OmKeyInfo.class);
    given(omKeyInfo3.getKeyName()).willReturn("key3");
    given(omKeyInfo3.getVolumeName()).willReturn("vol1");
    given(omKeyInfo3.getBucketName()).willReturn("bucket1");
    given(omKeyInfo3.getDataSize()).willReturn(1125899906842624L * 4); // 4PB

    OMMetadataManager omMetadataManager = mock(OmMetadataManagerImpl.class);
    TypedTable<String, OmKeyInfo> keyTable = mock(TypedTable.class);

    TypedTable.TypedTableIterator mockKeyIter = mock(TypedTable
        .TypedTableIterator.class);
    TypedTable.TypedKeyValue mockKeyValue = mock(
        TypedTable.TypedKeyValue.class);

    when(keyTable.iterator()).thenReturn(mockKeyIter);
    when(omMetadataManager.getKeyTable(getBucketLayout())).thenReturn(keyTable);
    when(mockKeyIter.hasNext())
        .thenReturn(true)
        .thenReturn(true)
        .thenReturn(true)
        .thenReturn(false);
    when(mockKeyIter.next()).thenReturn(mockKeyValue);
    when(mockKeyValue.getValue())
        .thenReturn(omKeyInfo1)
        .thenReturn(omKeyInfo2)
        .thenReturn(omKeyInfo3);

    // Reprocess could be called from table having existing entries. Adding
    // an entry to simulate that.
    fileCountBySizeDao.insert(
        new FileCountBySize("vol1", "bucket1", 1024L, 10L));

    Pair<String, Boolean> result =
        fileSizeCountTask.reprocess(omMetadataManager);
    assertTrue(result.getRight());

    assertEquals(3, fileCountBySizeDao.count());
    Record3<String, String, Long> recordToFind = dslContext
        .newRecord(FILE_COUNT_BY_SIZE.VOLUME,
        FILE_COUNT_BY_SIZE.BUCKET,
        FILE_COUNT_BY_SIZE.FILE_SIZE)
        .value1("vol1")
        .value2("bucket1")
        .value3(1024L);
    assertEquals(1L,
        fileCountBySizeDao.findById(recordToFind).getCount().longValue());
    // file size upper bound for 100000L is 131072L (next highest power of 2)
    recordToFind.value3(131072L);
    assertEquals(1L,
        fileCountBySizeDao.findById(recordToFind).getCount().longValue());
    // file size upper bound for 4PB is Long.MAX_VALUE
    recordToFind.value3(Long.MAX_VALUE);
    assertEquals(1L,
        fileCountBySizeDao.findById(recordToFind).getCount().longValue());
  }

}