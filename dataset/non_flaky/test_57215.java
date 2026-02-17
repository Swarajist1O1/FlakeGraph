class DummyClass_57215 {
  @Test
  public void testPut() throws Exception {
    OzoneConfiguration configuration = createNewTestPath();
    OmMetadataManagerImpl metaMgr = new OmMetadataManagerImpl(configuration);

    // Create 1 volume, 2 keys and write to source OM DB.
    String volumeKey = metaMgr.getVolumeKey("sampleVol");
    OmVolumeArgs args =
        OmVolumeArgs.newBuilder()
            .setVolume("sampleVol")
            .setAdminName("bilbo")
            .setOwnerName("bilbo")
            .build();
    metaMgr.getVolumeTable().put(volumeKey, args);

    OmKeyInfo firstKey = getOmKeyInfo("sampleVol", "bucketOne", "key_one");
    metaMgr.getKeyTable(getBucketLayout())
        .put("/sampleVol/bucketOne/key_one", firstKey);

    OmKeyInfo secondKey = getOmKeyInfo("sampleVol", "bucketOne", "key_two");
    metaMgr.getKeyTable(getBucketLayout())
        .put("/sampleVol/bucketOne/key_two", secondKey);

    // Write the secondKey to the target OM DB.
    OzoneConfiguration conf2 = createNewTestPath();
    OmMetadataManagerImpl reconOmmetaMgr = new OmMetadataManagerImpl(conf2);
    reconOmmetaMgr.getKeyTable(getBucketLayout())
        .put("/sampleVol/bucketOne/key_two", secondKey);

    RDBStore rdbStore = (RDBStore) metaMgr.getStore();
    RocksDB rocksDB = rdbStore.getDb();
    // Get all updates from source DB. (3 PUTs)
    TransactionLogIterator transactionLogIterator =
        rocksDB.getUpdatesSince(0);
    List<byte[]> writeBatches = new ArrayList<>();

    while(transactionLogIterator.isValid()) {
      TransactionLogIterator.BatchResult result =
          transactionLogIterator.getBatch();
      result.writeBatch().markWalTerminationPoint();
      WriteBatch writeBatch = result.writeBatch();
      writeBatches.add(writeBatch.data());
      transactionLogIterator.next();
    }

    // OMDBUpdatesHandler has access to target DB. Hence it has only the
    // "secondKey".
    OMDBUpdatesHandler omdbUpdatesHandler =
        new OMDBUpdatesHandler(reconOmmetaMgr);
    for (byte[] data : writeBatches) {
      WriteBatch writeBatch = new WriteBatch(data);
      // Capture the 3 PUT events from source DB.
      writeBatch.iterate(omdbUpdatesHandler);
    }

    List<OMDBUpdateEvent> events = omdbUpdatesHandler.getEvents();
    assertEquals(3, events.size());

    OMDBUpdateEvent volEvent = events.get(0);
    assertEquals(PUT, volEvent.getAction());
    assertEquals(volumeKey, volEvent.getKey());
    assertEquals(args.getVolume(), ((OmVolumeArgs)volEvent.getValue())
        .getVolume());

    OMDBUpdateEvent keyEvent = events.get(1);
    assertEquals(PUT, keyEvent.getAction());
    assertEquals("/sampleVol/bucketOne/key_one", keyEvent.getKey());
    assertNull(keyEvent.getOldValue());

    OMDBUpdateEvent updateEvent = events.get(2);
    assertEquals(UPDATE, updateEvent.getAction());
    assertEquals("/sampleVol/bucketOne/key_two", updateEvent.getKey());
    assertNotNull(updateEvent.getOldValue());
    assertEquals(secondKey.getKeyName(),
        ((OmKeyInfo)updateEvent.getOldValue()).getKeyName());
  }

}