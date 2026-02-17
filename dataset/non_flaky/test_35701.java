class DummyClass_35701 {
  @Test
  public void testScanAndDeleteNewMetadata() throws Exception {
    TransactionRunner transactionRunner = injector.getInstance(TransactionRunner.class);

    FileMetaDataWriter fileMetaDataWriter = new FileMetaDataWriter(transactionRunner);
    FileMetadataCleaner fileMetadataCleaner = new FileMetadataCleaner(transactionRunner);
    try {
      long currentTime = System.currentTimeMillis();
      long eventTimestamp = currentTime - 100;
      LogPathIdentifier logPathIdentifier = new LogPathIdentifier("testNs2", "testApp", "testFlow");
      LocationFactory locationFactory = injector.getInstance(LocationFactory.class);
      List<String> expected = new ArrayList<>();
      for (int i = 0; i < 100; i++) {
        Location location = locationFactory.create("testFlowFile" + i);
        // values : event time is 100ms behind current timestamp
        fileMetaDataWriter.writeMetaData(logPathIdentifier, eventTimestamp + i, currentTime + i, location);
        expected.add(location.toURI().getPath());
      }

      long tillTime = currentTime + 50;
      List<FileMetadataCleaner.DeletedEntry> deletedEntries =
        fileMetadataCleaner.scanAndGetFilesToDelete(tillTime, 100);
      // we should have deleted 51 rows, till time is inclusive
      Assert.assertEquals(51, deletedEntries.size());
      int count = 0;
      for (FileMetadataCleaner.DeletedEntry deletedEntry : deletedEntries) {
        Assert.assertEquals(expected.get(count), deletedEntry.getPath());
        count += 1;
      }
      // now add 10 entries for spark
      logPathIdentifier = new LogPathIdentifier("testNs2", "testApp", "testSpark");
      expected = new ArrayList<>();

      for (int i = 0; i < 10; i++) {
        Location location = locationFactory.create("testSparkFile" + i);
        // values : event time is 100ms behind current timestamp
        fileMetaDataWriter.writeMetaData(logPathIdentifier, eventTimestamp + i, currentTime + i, location);
        expected.add(location.toURI().getPath());
      }

      // lets keep the same till time - this should only delete the spark entries now
      deletedEntries = fileMetadataCleaner.scanAndGetFilesToDelete(tillTime, 100);
      // we should have deleted 51 rows, till time is inclusive
      Assert.assertEquals(10, deletedEntries.size());
      count = 0;
      for (FileMetadataCleaner.DeletedEntry deletedEntry : deletedEntries) {
        Assert.assertEquals(expected.get(count), deletedEntry.getPath());
        count += 1;
      }

      // now add 10 entries in mr context in time range 60-70
      logPathIdentifier = new LogPathIdentifier("testNs2", "testApp", "testMr");
      expected = new ArrayList<>();

      // flow should come up at the beginning in the expected list
      for (int i = 51; i <= 70; i++) {
        expected.add(locationFactory.create("testFlowFile" + i).toURI().getPath());
      }

      for (int i = 0; i < 10; i++) {
        Location location = locationFactory.create("testMrFile" + i);
        // values : event time is 100ms behind current timestamp
        fileMetaDataWriter.writeMetaData(logPathIdentifier, eventTimestamp + i, currentTime + i, location);
        expected.add(location.toURI().getPath());
      }

      List<String> nextExpected = new ArrayList<>();
      logPathIdentifier = new LogPathIdentifier("testNs2", "testApp", "testCustomAction");
      for (int i = 90; i < 100; i++) {
        Location location = locationFactory.create("testActionFile" + i);
        // values : event time is 100ms behind current timestamp
        fileMetaDataWriter.writeMetaData(logPathIdentifier, eventTimestamp + i, currentTime + i, location);
        nextExpected.add(location.toURI().getPath());
      }

      tillTime = currentTime + 70;
      // lets delete till 70.
      deletedEntries = fileMetadataCleaner.scanAndGetFilesToDelete(tillTime, 100);
      // we should have deleted 51-70 files of flow and 0-9 files of spark files in that order and 0 files of action.
      Assert.assertEquals(30, deletedEntries.size());
      count = 0;
      for (FileMetadataCleaner.DeletedEntry deletedEntry : deletedEntries) {
        Assert.assertEquals(expected.get(count), deletedEntry.getPath());
        count += 1;
      }

      // now delete till currentTime + 100, this should delete all remaining entries.
      // custom action should come first and then flow entries

      tillTime = currentTime + 100;
      // lets delete till 100.
      deletedEntries = fileMetadataCleaner.scanAndGetFilesToDelete(tillTime, 100);
      // we should have deleted 90-99 of custom action(10) 71-99 (29) files of flow.
      for (int i = 71; i < 100; i++) {
        nextExpected.add(locationFactory.create("testFlowFile" + i).toURI().getPath());
      }
      Assert.assertEquals(39, deletedEntries.size());
      count = 0;
      for (FileMetadataCleaner.DeletedEntry deletedEntry : deletedEntries) {
        Assert.assertEquals(nextExpected.get(count), deletedEntry.getPath());
        count += 1;
      }

      // now lets do a delete with till time  = currentTime + 1000, this should return empty result
      tillTime = currentTime + 1000;
      deletedEntries = fileMetadataCleaner.scanAndGetFilesToDelete(tillTime, 100);
      Assert.assertEquals(0, deletedEntries.size());
    } finally {
      // cleanup meta
      deleteAllMetaEntries(transactionRunner);
    }
  }

}