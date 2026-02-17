class DummyClass_332 {
  @Test (timeout = 60000)
  public void testNfsWriteNN2() throws Exception {
    HdfsFileStatus status = nn2.getRpcServer().getFileInfo("/user2/write2");
    int namenodeId = Nfs3Utils.getNamenodeId(config, hdfs2.getUri());
    testNfsWriteResponse(status.getFileId(), namenodeId);
  }

}