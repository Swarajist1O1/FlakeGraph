class DummyClass_35703 {
  @Test
  public void testWithBatchSizeLargerThanNumOfFiles() throws Exception {
    TransactionRunner transactionRunner = injector.getInstance(TransactionRunner.class);

    FileMetaDataWriter fileMetaDataWriter = new FileMetaDataWriter(transactionRunner);
    FileMetaDataReader fileMetadataReader = injector.getInstance(FileMetaDataReader.class);
    FileMetadataCleaner fileMetadataCleaner = new FileMetadataCleaner(transactionRunner);
    try {
      LogPathIdentifier identifier = new LogPathIdentifier(NamespaceId.DEFAULT.getNamespace(),
                                                           "testApp", String.format("testFlow%s", 0));

      LocationFactory locationFactory = injector.getInstance(LocationFactory.class);
      Location location = locationFactory.create(TMP_FOLDER.newFolder().getPath()).append("/logs");
      long currentTime = System.currentTimeMillis();
      long newCurrentTime = currentTime + 100;

      for (int j = 0; j < 10; j++) {
        fileMetaDataWriter.writeMetaData(identifier, newCurrentTime + j, newCurrentTime + j,
                                         location.append("testFileNew" + Integer.toString(j)));
      }

      List<LogLocation> locations;
      locations = fileMetadataReader.listFiles(identifier, newCurrentTime, newCurrentTime + 10);
      // should include files from currentTime (0..9)
      Assert.assertEquals(10, locations.size());

      long tillTime = newCurrentTime + 4;
      List<FileMetadataCleaner.DeletedEntry> deleteEntries =
        fileMetadataCleaner.scanAndGetFilesToDelete(tillTime, 1000);
      Assert.assertEquals(5, deleteEntries.size());
    } finally {
      // cleanup meta
      deleteAllMetaEntries(transactionRunner);
    }
  }

}