class DummyClass_334 {
  @Test (timeout = 60000)
  public void testNfsRenameSingleNN() throws Exception {
    HdfsFileStatus fromFileStatus = nn1.getRpcServer().getFileInfo("/user1");
    int fromNNId = Nfs3Utils.getNamenodeId(config, hdfs1.getUri());
    FileHandle fromHandle =
        new FileHandle(fromFileStatus.getFileId(), fromNNId);

    HdfsFileStatus statusBeforeRename =
        nn1.getRpcServer().getFileInfo("/user1/renameSingleNN");
    Assert.assertEquals(statusBeforeRename.isDirectory(), false);

    testNfsRename(fromHandle, "renameSingleNN",
        fromHandle, "renameSingleNNSucess", Nfs3Status.NFS3_OK);

    HdfsFileStatus statusAfterRename =
        nn1.getRpcServer().getFileInfo("/user1/renameSingleNNSucess");
    Assert.assertEquals(statusAfterRename.isDirectory(), false);

    statusAfterRename =
        nn1.getRpcServer().getFileInfo("/user1/renameSingleNN");
    Assert.assertEquals(statusAfterRename, null);
  }

}