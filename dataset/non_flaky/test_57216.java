class DummyClass_57216 {
  @Test
  public void testDelete() throws Exception {

    OzoneConfiguration configuration = createNewTestPath();
    OmMetadataManagerImpl metaMgr = new OmMetadataManagerImpl(configuration);

    OzoneConfiguration conf2 = createNewTestPath();
    OmMetadataManagerImpl metaMgrCopy = new OmMetadataManagerImpl(conf2);

    // Write 1 volume, 1 key into source and target OM DBs.
    String volumeKey = metaMgr.getVolumeKey("sampleVol");
    String nonExistVolumeKey = metaMgr.getVolumeKey("nonExistingVolume");
    OmVolumeArgs args =
        OmVolumeArgs.newBuilder()
            .setVolume("sampleVol")
            .setAdminName("bilbo")
            .setOwnerName("bilbo")
            .build();
    metaMgr.getVolumeTable().put(volumeKey, args);
    metaMgrCopy.getVolumeTable().put(volumeKey, args);

    OmKeyInfo omKeyInfo = getOmKeyInfo("sampleVol", "bucketOne", "key_one");
    metaMgr.getKeyTable(getBucketLayout())
        .put("/sampleVol/bucketOne/key_one", omKeyInfo);
    metaMgrCopy.getKeyTable(getBucketLayout())
        .put("/sampleVol/bucketOne/key_one", omKeyInfo);

    // Delete the volume and key from target DB.
    metaMgr.getKeyTable(getBucketLayout())
        .delete("/sampleVol/bucketOne/key_one");
    metaMgr.getVolumeTable().delete(volumeKey);
    // Delete a non-existing volume and key
    metaMgr.getKeyTable(getBucketLayout())
        .delete("/sampleVol/bucketOne/key_two");
    metaMgr.getVolumeTable().delete(metaMgr.getVolumeKey("nonExistingVolume"));

    RDBStore rdbStore = (RDBStore) metaMgr.getStore();
    RocksDB rocksDB = rdbStore.getDb();
    TransactionLogIterator transactionLogIterator =
        rocksDB.getUpdatesSince(3);
    List<byte[]> writeBatches = new ArrayList<>();

    while(transactionLogIterator.isValid()) {
      TransactionLogIterator.BatchResult result =
          transactionLogIterator.getBatch();
      result.writeBatch().markWalTerminationPoint();
      WriteBatch writeBatch = result.writeBatch();
      writeBatches.add(writeBatch.data());
      transactionLogIterator.next();
    }

    // OMDBUpdatesHandler has access to target DB. So it has the volume and
    // key.
    OMDBUpdatesHandler omdbUpdatesHandler =
        new OMDBUpdatesHandler(metaMgrCopy);
    for (byte[] data : writeBatches) {
      WriteBatch writeBatch = new WriteBatch(data);
      writeBatch.iterate(omdbUpdatesHandler);
    }

    List<OMDBUpdateEvent> events = omdbUpdatesHandler.getEvents();
    assertEquals(4, events.size());

    OMDBUpdateEvent keyEvent = events.get(0);
    assertEquals(OMDBUpdateEvent.OMDBUpdateAction.DELETE, keyEvent.getAction());
    assertEquals("/sampleVol/bucketOne/key_one", keyEvent.getKey());
    assertEquals(omKeyInfo, keyEvent.getValue());

    OMDBUpdateEvent volEvent = events.get(1);
    assertEquals(OMDBUpdateEvent.OMDBUpdateAction.DELETE, volEvent.getAction());
    assertEquals(volumeKey, volEvent.getKey());
    assertNotNull(volEvent.getValue());
    OmVolumeArgs volumeInfo = (OmVolumeArgs) volEvent.getValue();
    assertEquals("sampleVol", volumeInfo.getVolume());

    // Assert the values of non existent keys are set to null.
    OMDBUpdateEvent nonExistKey = events.get(2);
    assertEquals(OMDBUpdateEvent.OMDBUpdateAction.DELETE,
        nonExistKey.getAction());
    assertEquals("/sampleVol/bucketOne/key_two", nonExistKey.getKey());
    assertNull(nonExistKey.getValue());

    OMDBUpdateEvent nonExistVolume = events.get(3);
    assertEquals(OMDBUpdateEvent.OMDBUpdateAction.DELETE,
        nonExistVolume.getAction());
    assertEquals(nonExistVolumeKey, nonExistVolume.getKey());
    assertNull(nonExistVolume.getValue());
  }

}