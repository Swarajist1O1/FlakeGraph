class DummyClass_333 {
  @Test (timeout = 60000)
  public void testNfsRenameMultiNN() throws Exception {
    HdfsFileStatus fromFileStatus = nn1.getRpcServer().getFileInfo("/user1");
    int fromNNId = Nfs3Utils.getNamenodeId(config, hdfs1.getUri());
    FileHandle fromHandle =
        new FileHandle(fromFileStatus.getFileId(), fromNNId);

    HdfsFileStatus toFileStatus = nn2.getRpcServer().getFileInfo("/user2");
    int toNNId = Nfs3Utils.getNamenodeId(config, hdfs2.getUri());
    FileHandle toHandle = new FileHandle(toFileStatus.getFileId(), toNNId);

    HdfsFileStatus statusBeforeRename =
        nn1.getRpcServer().getFileInfo("/user1/renameMultiNN");
    Assert.assertEquals(statusBeforeRename.isDirectory(), false);

    testNfsRename(fromHandle, "renameMultiNN",
        toHandle, "renameMultiNNFail", Nfs3Status.NFS3ERR_INVAL);

    HdfsFileStatus statusAfterRename =
        nn2.getRpcServer().getFileInfo("/user2/renameMultiNNFail");
    Assert.assertEquals(statusAfterRename, null);

    statusAfterRename = nn1.getRpcServer().getFileInfo("/user1/renameMultiNN");
    Assert.assertEquals(statusAfterRename.isDirectory(), false);
  }

}