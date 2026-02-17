class DummyClass_328 {
  @Test (timeout = 60000)
  public void testNfsAccessNN1() throws Exception {
    HdfsFileStatus status = nn1.getRpcServer().getFileInfo("/user1/file1");
    int namenodeId = Nfs3Utils.getNamenodeId(config, hdfs1.getUri());
    testNfsGetAttrResponse(status.getFileId(), namenodeId, Nfs3Status.NFS3_OK);
  }

}