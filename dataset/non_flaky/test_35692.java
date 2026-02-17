class DummyClass_35692 {
  @Test
  public void testFileMetadataReadWriteAcrossFormats() throws Exception {
    TransactionRunner transactionRunner = injector.getInstance(TransactionRunner.class);
    FileMetaDataWriter fileMetaDataWriter = new FileMetaDataWriter(transactionRunner);
    LogPathIdentifier logPathIdentifier =
      new LogPathIdentifier(NamespaceId.DEFAULT.getNamespace(), "testApp", "testFlow");
    LocationFactory locationFactory = injector.getInstance(LocationFactory.class);
    Location location = locationFactory.create(TMP_FOLDER.newFolder().getPath()).append("/logs");
    long currentTime = System.currentTimeMillis();

    long eventTime = currentTime + 20;
    long newCurrentTime = currentTime + 100;
    // 10 files in new format
    for (int i = 1; i <= 10; i++) {
      fileMetaDataWriter.writeMetaData(logPathIdentifier, eventTime + i, newCurrentTime + i,
                                       location.append("testFileNew" + Integer.toString(i)));
    }
    // reader test
    FileMetaDataReader fileMetadataReader = injector.getInstance(FileMetaDataReader.class);

    // scan only in new files time range
    List<LogLocation> locations = fileMetadataReader.listFiles(logPathIdentifier, eventTime + 2, eventTime + 6);
    // should include files from currentTime (1..6)
    Assert.assertEquals(6, locations.size());
    for (LogLocation logLocation : locations) {
      Assert.assertEquals(LogLocation.VERSION_1, logLocation.getFrameworkVersion());
    }

    // scan time range across formats
    locations = fileMetadataReader.listFiles(logPathIdentifier, currentTime + 2, eventTime + 6);
    // should include files from new range (1..6)
    Assert.assertEquals(6, locations.size());

    for (int i = 0; i < locations.size(); i++) {
      Assert.assertEquals(LogLocation.VERSION_1, locations.get(i).getFrameworkVersion());
      Assert.assertEquals(location.append("testFileNew" + Integer.toString(i + 1)), locations.get(i).getLocation());
    }
  }

}