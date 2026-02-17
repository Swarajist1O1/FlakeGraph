class DummyClass_35691 {
  @Test
  public void testFileMetadataReadWrite() throws Exception {
    TransactionRunner transactionRunner = injector.getInstance(TransactionRunner.class);
    FileMetaDataWriter fileMetaDataWriter = new FileMetaDataWriter(transactionRunner);
    LogPathIdentifier logPathIdentifier =
      new LogPathIdentifier(NamespaceId.DEFAULT.getNamespace(), "testApp", "testFlow");
    LocationFactory locationFactory = injector.getInstance(LocationFactory.class);
    Location location = locationFactory.create(TMP_FOLDER.newFolder().getPath()).append("/logs");
    long currentTime = System.currentTimeMillis();
    for (int i = 10; i <= 100; i += 10) {
      // i is the event time
      fileMetaDataWriter.writeMetaData(logPathIdentifier, i, currentTime,
                                       location.append(Integer.toString(i)));
    }

    // for the timestamp 80, add new new log path id with different current time.

    fileMetaDataWriter.writeMetaData(logPathIdentifier, 80, currentTime + 1,
                                     location.append("81"));

    fileMetaDataWriter.writeMetaData(logPathIdentifier, 80, currentTime + 2,
                                     location.append("82"));

    // reader test
    FileMetaDataReader fileMetadataReader = injector.getInstance(FileMetaDataReader.class);

    Assert.assertEquals(12, fileMetadataReader.listFiles(logPathIdentifier, 0, 100).size());
    Assert.assertEquals(5, fileMetadataReader.listFiles(logPathIdentifier, 20, 50).size());
    Assert.assertEquals(2, fileMetadataReader.listFiles(logPathIdentifier, 100, 150).size());

    // should include the latest file with event start time 80.
    List<LogLocation> locationList = fileMetadataReader.listFiles(logPathIdentifier, 81, 85);
    Assert.assertEquals(1, locationList.size());
    Assert.assertEquals(80, locationList.get(0).getEventTimeMs());
    Assert.assertEquals(location.append("82"), locationList.get(0).getLocation());

    Assert.assertEquals(1, fileMetadataReader.listFiles(logPathIdentifier, 150, 1000).size());
  }

}