class DummyClass_76921 {
  @Test
  public void odfsConfigurationTest() {
    SparkConf conf = new SparkConf();
    Configuration conf1 = RssShuffleUtils.newHadoopConfiguration(conf);
    assertFalse(conf1.getBoolean("dfs.namenode.odfs.enable", false));
    assertEquals("org.apache.hadoop.fs.Hdfs", conf1.get("fs.AbstractFileSystem.hdfs.impl"));

    conf.set(RssClientConfig.RSS_OZONE_DFS_NAMENODE_ODFS_ENABLE, "true");
    conf1 = RssShuffleUtils.newHadoopConfiguration(conf);
    assertTrue(conf1.getBoolean("dfs.namenode.odfs.enable", false));
    assertEquals("org.apache.hadoop.odfs.HdfsOdfsFilesystem", conf1.get("fs.hdfs.impl"));
    assertEquals("org.apache.hadoop.odfs.HdfsOdfs", conf1.get("fs.AbstractFileSystem.hdfs.impl"));

    conf.set(RssClientConfig.RSS_OZONE_FS_HDFS_IMPL, "expect_odfs_impl");
    conf.set(RssClientConfig.RSS_OZONE_FS_ABSTRACT_FILE_SYSTEM_HDFS_IMPL, "expect_odfs_abstract_impl");
    conf1 = RssShuffleUtils.newHadoopConfiguration(conf);
    assertEquals("expect_odfs_impl", conf1.get("fs.hdfs.impl"));
    assertEquals("expect_odfs_abstract_impl", conf1.get("fs.AbstractFileSystem.hdfs.impl"));
  }

}