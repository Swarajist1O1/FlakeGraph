class DummyClass_327 {
  @Test
  public void testFileStatus() throws Exception {
    HdfsFileStatus status = nn1.getRpcServer().getFileInfo("/user1/file1");
    FileStatus st = viewFs.getFileStatus(new Path("/hdfs1/file1"));
    Assert.assertEquals(st.isDirectory(), status.isDirectory());

    HdfsFileStatus status2 = nn2.getRpcServer().getFileInfo("/user2/dir2");
    FileStatus st2 = viewFs.getFileStatus(new Path("/hdfs2/dir2"));
    Assert.assertEquals(st2.isDirectory(), status2.isDirectory());
  }

}