class DummyClass_106584 {
  @Test
  public void missingBoth() {
    sConf.set(PropertyKey.UNDERFS_HDFS_CONFIGURATION, "/conf/");
    HdfsConfValidationTask task = new HdfsConfValidationTask("hdfs://namenode:9000/alluxio", sConf);
    ValidationTaskResult result = task.loadHdfsConfig();
    assertEquals(result.getState(), ValidationUtils.State.SKIPPED);
    assertThat(result.getResult(), containsString("hdfs-site.xml is not configured"));
    assertThat(result.getResult(), containsString("core-site.xml is not configured"));
    assertThat(result.getAdvice(), containsString("hdfs-site.xml"));
    assertThat(result.getAdvice(), containsString("core-site.xml"));
  }

}