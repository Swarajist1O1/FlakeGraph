class DummyClass_326 {
  @Test
  public void testPaths() throws Exception {
    Assert.assertEquals(hdfs1.resolvePath(new Path("/user1/file1")),
        viewFs.resolvePath(new Path("/hdfs1/file1")));
    Assert.assertEquals(hdfs1.resolvePath(new Path("/user1/file2")),
        viewFs.resolvePath(new Path("/hdfs1/file2")));
    Assert.assertEquals(hdfs2.resolvePath(new Path("/user2/dir2")),
        viewFs.resolvePath(new Path("/hdfs2/dir2")));
  }

}