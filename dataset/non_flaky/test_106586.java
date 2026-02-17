class DummyClass_106586 {
  @Test
  public void inconsistentConf() {
    String hdfsSite = Paths.get(sTestDir.toPath().toString(), "hdfs-site.xml").toString();
    ValidationTestUtils.writeXML(hdfsSite, ImmutableMap.of("key1", "value2"));

    String coreSite = Paths.get(sTestDir.toPath().toString(), "core-site.xml").toString();
    ValidationTestUtils.writeXML(coreSite, ImmutableMap.of("key1", "value1"));

    sConf.set(PropertyKey.UNDERFS_HDFS_CONFIGURATION,
            hdfsSite + HdfsConfValidationTask.SEPARATOR + coreSite);
    HdfsConfValidationTask task =
            new HdfsConfValidationTask("hdfs://namenode:9000/alluxio", sConf);
    ValidationTaskResult result = task.validateImpl(ImmutableMap.of());

    assertEquals(ValidationUtils.State.FAILED, result.getState());
    assertThat(result.getResult(), containsString("key1"));
    assertThat(result.getResult(), containsString("value1 in core-site.xml"));
    assertThat(result.getResult(), containsString("value2 in hdfs-site.xml"));
    assertThat(result.getAdvice(), containsString("fix the inconsistency"));
  }

}