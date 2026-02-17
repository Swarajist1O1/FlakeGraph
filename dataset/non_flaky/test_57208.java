class DummyClass_57208 {
  @Test
  public void testReprocessAtScale() throws IOException {
    // generate mocks for 2 volumes, 500 buckets each volume
    // and 42 keys in each bucket.
    List<OmKeyInfo> omKeyInfoList = new ArrayList<>();
    List<Boolean> hasNextAnswer = new ArrayList<>();
    for (int volIndex = 1; volIndex <= 2; volIndex++) {
      for (int bktIndex = 1; bktIndex <= 500; bktIndex++) {
        for (int keyIndex = 1; keyIndex <= 42; keyIndex++) {
          OmKeyInfo omKeyInfo = mock(OmKeyInfo.class);
          given(omKeyInfo.getKeyName()).willReturn("key" + keyIndex);
          given(omKeyInfo.getVolumeName()).willReturn("vol" + volIndex);
          given(omKeyInfo.getBucketName()).willReturn("bucket" + bktIndex);
          // Place keys in each bin
          long fileSize = (long)Math.pow(2, keyIndex + 9) - 1L;
          given(omKeyInfo.getDataSize()).willReturn(fileSize);
          omKeyInfoList.add(omKeyInfo);
          hasNextAnswer.add(true);
        }
      }
    }
    hasNextAnswer.add(false);

    OMMetadataManager omMetadataManager = mock(OmMetadataManagerImpl.class);
    TypedTable<String, OmKeyInfo> keyTable = mock(TypedTable.class);

    TypedTable.TypedTableIterator mockKeyIter = mock(TypedTable
        .TypedTableIterator.class);
    TypedTable.TypedKeyValue mockKeyValue = mock(
        TypedTable.TypedKeyValue.class);

    when(keyTable.iterator()).thenReturn(mockKeyIter);
    when(omMetadataManager.getKeyTable(getBucketLayout())).thenReturn(keyTable);
    when(mockKeyIter.hasNext())
        .thenAnswer(AdditionalAnswers.returnsElementsOf(hasNextAnswer));
    when(mockKeyIter.next()).thenReturn(mockKeyValue);
    when(mockKeyValue.getValue())
        .thenAnswer(AdditionalAnswers.returnsElementsOf(omKeyInfoList));

    Pair<String, Boolean> result =
        fileSizeCountTask.reprocess(omMetadataManager);
    assertTrue(result.getRight());

    // 2 volumes * 500 buckets * 42 bins = 42000 rows
    assertEquals(42000, fileCountBySizeDao.count());
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
    recordToFind.value1("vol1");
    recordToFind.value3(131072L);
    assertEquals(1L,
        fileCountBySizeDao.findById(recordToFind).getCount().longValue());
    recordToFind.value2("bucket500");
    recordToFind.value3(Long.MAX_VALUE);
    assertEquals(1L,
        fileCountBySizeDao.findById(recordToFind).getCount().longValue());
  }

}