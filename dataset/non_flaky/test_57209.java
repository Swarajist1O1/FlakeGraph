class DummyClass_57209 {
  @Test
  public void testProcessAtScale() {
    // Write 10000 keys.
    List<OMDBUpdateEvent> omDbEventList = new ArrayList<>();
    List<OmKeyInfo> omKeyInfoList = new ArrayList<>();
    for (int volIndex = 1; volIndex <= 10; volIndex++) {
      for (int bktIndex = 1; bktIndex <= 100; bktIndex++) {
        for (int keyIndex = 1; keyIndex <= 10; keyIndex++) {
          OmKeyInfo omKeyInfo = mock(OmKeyInfo.class);
          given(omKeyInfo.getKeyName()).willReturn("key" + keyIndex);
          given(omKeyInfo.getVolumeName()).willReturn("vol" + volIndex);
          given(omKeyInfo.getBucketName()).willReturn("bucket" + bktIndex);
          // Place keys in each bin
          long fileSize = (long)Math.pow(2, keyIndex + 9) - 1L;
          given(omKeyInfo.getDataSize()).willReturn(fileSize);
          omKeyInfoList.add(omKeyInfo);
          omDbEventList.add(new OMUpdateEventBuilder()
              .setAction(PUT)
              .setKey("key" + keyIndex)
              .setValue(omKeyInfo)
              .setTable(OmMetadataManagerImpl.KEY_TABLE)
              .build());
        }
      }
    }

    OMUpdateEventBatch omUpdateEventBatch =
        new OMUpdateEventBatch(omDbEventList);
    fileSizeCountTask.process(omUpdateEventBatch);

    // Verify 2 keys are in correct bins.
    assertEquals(10000, fileCountBySizeDao.count());
    Record3<String, String, Long> recordToFind = dslContext
        .newRecord(FILE_COUNT_BY_SIZE.VOLUME,
            FILE_COUNT_BY_SIZE.BUCKET,
            FILE_COUNT_BY_SIZE.FILE_SIZE)
        .value1("vol1")
        .value2("bucket1")
        .value3(2048L);
    assertEquals(1L,
        fileCountBySizeDao.findById(recordToFind).getCount().longValue());
    recordToFind.value1("vol10");
    recordToFind.value2("bucket100");
    // file size upper bound for 10000L is 16384L (next highest power of 2)
    recordToFind.value3(16384L);
    assertEquals(1L,
        fileCountBySizeDao.findById(recordToFind).getCount().longValue());

    // Process 500 deletes and 500 updates
    omDbEventList = new ArrayList<>();
    for (int volIndex = 1; volIndex <= 1; volIndex++) {
      for (int bktIndex = 1; bktIndex <= 100; bktIndex++) {
        for (int keyIndex = 1; keyIndex <= 10; keyIndex++) {
          OmKeyInfo omKeyInfo = mock(OmKeyInfo.class);
          given(omKeyInfo.getKeyName()).willReturn("key" + keyIndex);
          given(omKeyInfo.getVolumeName()).willReturn("vol" + volIndex);
          given(omKeyInfo.getBucketName()).willReturn("bucket" + bktIndex);
          if (keyIndex <= 5) {
            long fileSize = (long)Math.pow(2, keyIndex + 9) - 1L;
            given(omKeyInfo.getDataSize()).willReturn(fileSize);
            omDbEventList.add(new OMUpdateEventBuilder()
                .setAction(DELETE)
                .setKey("key" + keyIndex)
                .setValue(omKeyInfo)
                .setTable(OmMetadataManagerImpl.KEY_TABLE)
                .build());
          } else {
            // update all the files with keyIndex > 5 to filesize 1023L
            // so that they get into first bin
            given(omKeyInfo.getDataSize()).willReturn(1023L);
            omDbEventList.add(new OMUpdateEventBuilder()
                .setAction(UPDATE)
                .setKey("key" + keyIndex)
                .setValue(omKeyInfo)
                .setTable(OmMetadataManagerImpl.KEY_TABLE)
                .setOldValue(
                    omKeyInfoList.get((volIndex * bktIndex) + keyIndex))
                .build());
          }
        }
      }
    }

    omUpdateEventBatch = new OMUpdateEventBatch(omDbEventList);
    fileSizeCountTask.process(omUpdateEventBatch);

    assertEquals(10000, fileCountBySizeDao.count());
    recordToFind = dslContext
        .newRecord(FILE_COUNT_BY_SIZE.VOLUME,
            FILE_COUNT_BY_SIZE.BUCKET,
            FILE_COUNT_BY_SIZE.FILE_SIZE)
        .value1("vol1")
        .value2("bucket1")
        .value3(1024L);
    // The update events on keys 6-10 should now put them under first bin 1024L
    assertEquals(5, fileCountBySizeDao.findById(recordToFind)
        .getCount().longValue());
    recordToFind.value2("bucket100");
    assertEquals(5, fileCountBySizeDao.findById(recordToFind)
        .getCount().longValue());
    recordToFind.value3(2048L);
    assertEquals(0, fileCountBySizeDao.findById(recordToFind)
        .getCount().longValue());
    // Volumes 2 - 10 should not be affected by this process
    recordToFind.value1("vol2");
    assertEquals(1, fileCountBySizeDao.findById(recordToFind)
        .getCount().longValue());
  }

}