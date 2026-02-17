class DummyClass_106669 {
  @Test
  public void free() throws Exception {
    AlluxioURI file = new AlluxioURI("/file");
    FreePOptions freeOptions = FreePOptions.newBuilder().setRecursive(true).build();
    mFileSystem.free(file, freeOptions);
    verify(mFileSystemMasterClient).free(file, FileSystemOptions.freeDefaults(mConf)
        .toBuilder().mergeFrom(freeOptions).build());

    verifyFilesystemContextAcquiredAndReleased();
  }

}