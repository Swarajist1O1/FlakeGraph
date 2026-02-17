class DummyClass_35702 {
  @Test
  public void testFileMetadataWithCommonContextPrefix() throws Exception {
    TransactionRunner transactionRunner = injector.getInstance(TransactionRunner.class);

    FileMetaDataWriter fileMetaDataWriter = new FileMetaDataWriter(transactionRunner);
    FileMetaDataReader fileMetadataReader = injector.getInstance(FileMetaDataReader.class);
    FileMetadataCleaner fileMetadataCleaner = new FileMetadataCleaner(transactionRunner);
    try {
      List<LogPathIdentifier> logPathIdentifiers = new ArrayList<>();
      // we write entries where program id is of format testFlow{1..20},
      // this should be able to scan and delete common prefix programs like testFlow1, testFlow10 during clenaup.
      for (int i = 1; i <= 20; i++) {
        logPathIdentifiers.add(new LogPathIdentifier(NamespaceId.DEFAULT.getNamespace(),
                                                     "testApp", String.format("testFlow%s", i)));
      }

      LocationFactory locationFactory = injector.getInstance(LocationFactory.class);
      Location location = locationFactory.create(TMP_FOLDER.newFolder().getPath()).append("/logs");
      long currentTime = System.currentTimeMillis();
      long newCurrentTime = currentTime + 100;

      for (int i = 1; i <= 20; i++) {
        LogPathIdentifier identifier = logPathIdentifiers.get(i - 1);
        for (int j = 0; j < 10; j++) {
          fileMetaDataWriter.writeMetaData(identifier, newCurrentTime + j, newCurrentTime + j,
                                           location.append("testFileNew" + Integer.toString(j)));
        }
      }

      List<LogLocation> locations;
      for (int i = 1; i <= 20; i++) {
        locations = fileMetadataReader.listFiles(logPathIdentifiers.get(i - 1),
                                                 newCurrentTime, newCurrentTime + 10);
        // should include files from currentTime (0..9)
        Assert.assertEquals(10, locations.size());
      }

      long tillTime = newCurrentTime + 4;
      List<FileMetadataCleaner.DeletedEntry> deleteEntries =
        fileMetadataCleaner.scanAndGetFilesToDelete(tillTime, 100);
      // 20 context, 5 entries each
      Assert.assertEquals(100, deleteEntries.size());
      for (int i = 1; i <= 20; i++) {
        locations = fileMetadataReader.listFiles(logPathIdentifiers.get(i - 1),
                                                 newCurrentTime, newCurrentTime + 10);
        // should include files from time (5..9)
        Assert.assertEquals(5, locations.size());
        int startIndex = 5;
        for (LogLocation logLocation : locations) {
          Assert.assertEquals(String.format("testFileNew%s", startIndex), logLocation.getLocation().getName());
          startIndex++;
        }
      }
    } finally {
      // cleanup meta
      deleteAllMetaEntries(transactionRunner);
    }
  }

}