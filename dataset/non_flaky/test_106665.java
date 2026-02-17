class DummyClass_106665 {
  @Test
  public void createFile() throws Exception {
    URIStatus status = new URIStatus(new FileInfo());
    AlluxioURI file = new AlluxioURI("/file");
    when(mFileSystemMasterClient.createFile(any(AlluxioURI.class), any(CreateFilePOptions.class)))
        .thenReturn(status);
    mFileSystem.createFile(file, CreateFilePOptions.getDefaultInstance());
    verify(mFileSystemMasterClient).createFile(file, FileSystemOptions.createFileDefaults(mConf)
            .toBuilder().mergeFrom(CreateFilePOptions.getDefaultInstance()).build());

    verifyFilesystemContextAcquiredAndReleased();
  }

}