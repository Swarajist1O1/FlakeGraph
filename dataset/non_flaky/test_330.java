class DummyClass_330 {
  @Test (timeout = 60000)
  public void testWrongNfsAccess() throws Exception {
    DFSTestUtil.createFile(viewFs, new Path("/hdfs1/file3"), 0, (short) 1, 0);
    HdfsFileStatus status = nn1.getRpcServer().getFileInfo("/user1/file3");
    int namenodeId = Nfs3Utils.getNamenodeId(config, hdfs2.getUri());
    testNfsGetAttrResponse(status.getFileId(), namenodeId,
        Nfs3Status.NFS3ERR_IO);
  }

}