class DummyClass_57207 {
  @Test
  public void testProcess() {
    // Write 2 keys.
    OmKeyInfo toBeDeletedKey = mock(OmKeyInfo.class);
    given(toBeDeletedKey.getVolumeName()).willReturn("vol1");
    given(toBeDeletedKey.getBucketName()).willReturn("bucket1");
    given(toBeDeletedKey.getKeyName()).willReturn("deletedKey");
    given(toBeDeletedKey.getDataSize()).willReturn(2000L); // Bin 1
    OMDBUpdateEvent event = new OMUpdateEventBuilder()
        .setAction(PUT)
        .setKey("deletedKey")
        .setValue(toBeDeletedKey)
        .setTable(OmMetadataManagerImpl.KEY_TABLE)
        .build();

    OmKeyInfo toBeUpdatedKey = mock(OmKeyInfo.class);
    given(toBeUpdatedKey.getVolumeName()).willReturn("vol1");
    given(toBeUpdatedKey.getBucketName()).willReturn("bucket1");
    given(toBeUpdatedKey.getKeyName()).willReturn("updatedKey");
    given(toBeUpdatedKey.getDataSize()).willReturn(10000L); // Bin 4
    OMDBUpdateEvent event2 = new OMUpdateEventBuilder()
        .setAction(PUT)
        .setKey("updatedKey")
        .setValue(toBeUpdatedKey)
        .setTable(OmMetadataManagerImpl.KEY_TABLE)
        .build();

    OMUpdateEventBatch omUpdateEventBatch =
        new OMUpdateEventBatch(Arrays.asList(event, event2));
    fileSizeCountTask.process(omUpdateEventBatch);

    // Verify 2 keys are in correct bins.
    assertEquals(2, fileCountBySizeDao.count());
    Record3<String, String, Long> recordToFind = dslContext
        .newRecord(FILE_COUNT_BY_SIZE.VOLUME,
            FILE_COUNT_BY_SIZE.BUCKET,
            FILE_COUNT_BY_SIZE.FILE_SIZE)
        .value1("vol1")
        .value2("bucket1")
        .value3(2048L);
    assertEquals(1L,
        fileCountBySizeDao.findById(recordToFind).getCount().longValue());
    // file size upper bound for 10000L is 16384L (next highest power of 2)
    recordToFind.value3(16384L);
    assertEquals(1L,
        fileCountBySizeDao.findById(recordToFind).getCount().longValue());

    // Add new key.
    OmKeyInfo newKey = mock(OmKeyInfo.class);
    given(newKey.getVolumeName()).willReturn("vol1");
    given(newKey.getBucketName()).willReturn("bucket1");
    given(newKey.getKeyName()).willReturn("newKey");
    given(newKey.getDataSize()).willReturn(1000L); // Bin 0
    OMDBUpdateEvent putEvent = new OMUpdateEventBuilder()
        .setAction(PUT)
        .setKey("newKey")
        .setValue(newKey)
        .setTable(OmMetadataManagerImpl.KEY_TABLE)
        .build();

    // Update existing key.
    OmKeyInfo updatedKey = mock(OmKeyInfo.class);
    given(updatedKey.getVolumeName()).willReturn("vol1");
    given(updatedKey.getBucketName()).willReturn("bucket1");
    given(updatedKey.getKeyName()).willReturn("updatedKey");
    given(updatedKey.getDataSize()).willReturn(50000L); // Bin 6
    OMDBUpdateEvent updateEvent = new OMUpdateEventBuilder()
        .setAction(UPDATE)
        .setKey("updatedKey")
        .setValue(updatedKey)
        .setOldValue(toBeUpdatedKey)
        .setTable(OmMetadataManagerImpl.KEY_TABLE)
        .build();

    // Delete another existing key.
    OMDBUpdateEvent deleteEvent = new OMUpdateEventBuilder()
        .setAction(DELETE)
        .setKey("deletedKey")
        .setValue(toBeDeletedKey)
        .setTable(OmMetadataManagerImpl.KEY_TABLE)
        .build();

    omUpdateEventBatch = new OMUpdateEventBatch(
        Arrays.asList(updateEvent, putEvent, deleteEvent));
    fileSizeCountTask.process(omUpdateEventBatch);

    assertEquals(4, fileCountBySizeDao.count());
    recordToFind.value3(1024L);
    assertEquals(1, fileCountBySizeDao.findById(recordToFind)
        .getCount().longValue());
    recordToFind.value3(2048L);
    assertEquals(0, fileCountBySizeDao.findById(recordToFind)
        .getCount().longValue());
    recordToFind.value3(16384L);
    assertEquals(0, fileCountBySizeDao.findById(recordToFind)
        .getCount().longValue());
    recordToFind.value3(65536L);
    assertEquals(1, fileCountBySizeDao.findById(recordToFind)
        .getCount().longValue());
  }

}