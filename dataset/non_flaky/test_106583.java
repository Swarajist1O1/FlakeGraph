class DummyClass_106583 {
  @Test
  public void missingHdfsSiteXML() {
    // Only prepare core-site.xml
    String coreSite = Paths.get(sTestDir.toPath().toString(), "core-site.xml").toString();
    ValidationTestUtils.writeXML(coreSite, ImmutableMap.of("key1", "value1"));

    sConf.set(PropertyKey.UNDERFS_HDFS_CONFIGURATION, coreSite);
    HdfsConfValidationTask task = new HdfsConfValidationTask("hdfs://namenode:9000/alluxio", sConf);
    ValidationTaskResult result = task.loadHdfsConfig();
    assertEquals(result.getState(), ValidationUtils.State.SKIPPED);
    assertThat(result.getResult(), containsString("hdfs-site.xml is not configured"));
    assertThat(result.getAdvice(), containsString("hdfs-site.xml"));
  }

}