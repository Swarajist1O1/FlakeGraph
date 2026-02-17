class DummyClass_35704 {
  @Test
  public void testLogCleanup() throws Exception {
    TransactionRunner transactionRunner = injector.getInstance(TransactionRunner.class);
    FileMetadataCleaner fileMetadataCleaner = new FileMetadataCleaner(transactionRunner);
    LocationFactory locationFactory = injector.getInstance(LocationFactory.class);
    long currentTime = System.currentTimeMillis();
    LogPathIdentifier logPathIdentifier = new LogPathIdentifier("testNs", "testApp", "testEntity");
    FileMetaDataWriter fileMetaDataWriter = new FileMetaDataWriter(transactionRunner);
    long startTime = currentTime - 5000;
    Location dirLocation = locationFactory.create("logs");
    dirLocation.mkdirs();
    // create 20 files, add them in past time range
    for (int i = 0; i < 20; i++) {
      Location location = dirLocation.append("test" + i);
      location.createNew();
      fileMetaDataWriter.writeMetaData(logPathIdentifier, startTime + i, startTime + i, location);
    }

    Assert.assertEquals(20, dirLocation.list().size());
    LogCleaner logCleaner = new LogCleaner(fileMetadataCleaner, locationFactory, 100, 60);
    logCleaner.run();
    FileMetaDataReader fileMetaDataReader = injector.getInstance(FileMetaDataReader.class);
    // all meta data should be deleted
    Assert.assertEquals(0, fileMetaDataReader.listFiles(logPathIdentifier, 0, System.currentTimeMillis()).size());
    // we are not asserting file existence as the delete could fail and we don't guarantee file deletion.
  }

}