class DummyClass_106582 {
  @Test
  public void missingCoreSiteXML() {
    // Only prepare hdfs-site.xml
    String hdfsSite = Paths.get(sTestDir.toPath().toString(), "hdfs-site.xml").toString();
    ValidationTestUtils.writeXML(hdfsSite, ImmutableMap.of("key1", "value1"));

    sConf.set(PropertyKey.UNDERFS_HDFS_CONFIGURATION, hdfsSite);
    HdfsConfValidationTask task = new HdfsConfValidationTask("hdfs://namenode:9000/alluxio", sConf);
    ValidationTaskResult result = task.loadHdfsConfig();
    assertEquals(result.getState(), ValidationUtils.State.SKIPPED);
    assertThat(result.getResult(), containsString("core-site.xml is not configured"));
    assertThat(result.getAdvice(), containsString("core-site.xml"));
  }

}