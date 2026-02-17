class DummyClass_329 {
  @Test (timeout = 60000)
  public void testNfsAccessNN2() throws Exception {
    HdfsFileStatus status = nn2.getRpcServer().getFileInfo("/user2/dir2");
    int namenodeId = Nfs3Utils.getNamenodeId(config, hdfs2.getUri());
    testNfsGetAttrResponse(status.getFileId(), namenodeId, Nfs3Status.NFS3_OK);
  }

}