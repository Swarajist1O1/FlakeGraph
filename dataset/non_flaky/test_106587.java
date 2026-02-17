class DummyClass_106587 {
  @Test
  public void validConf() {
    String hdfsSite = Paths.get(sTestDir.toPath().toString(), "hdfs-site.xml").toString();
    ValidationTestUtils.writeXML(hdfsSite, ImmutableMap.of("key1", "value1", "key3", "value3"));

    String coreSite = Paths.get(sTestDir.toPath().toString(), "core-site.xml").toString();
    ValidationTestUtils.writeXML(coreSite, ImmutableMap.of("key1", "value1", "key4", "value4"));

    sConf.set(PropertyKey.UNDERFS_HDFS_CONFIGURATION,
            hdfsSite + HdfsConfValidationTask.SEPARATOR + coreSite);
    HdfsConfValidationTask task =
            new HdfsConfValidationTask("hdfs://namenode:9000/alluxio", sConf);
    ValidationTaskResult result = task.validateImpl(ImmutableMap.of());

    assertEquals(ValidationUtils.State.OK, result.getState());
  }

}