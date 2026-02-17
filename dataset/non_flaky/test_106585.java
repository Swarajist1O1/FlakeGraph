class DummyClass_106585 {
  @Test
  public void cannotParseCoreSiteXml() throws IOException {
    String hdfsSite = Paths.get(sTestDir.toPath().toString(), "hdfs-site.xml").toString();
    ValidationTestUtils.writeXML(hdfsSite, ImmutableMap.of("key2", "value2"));
    RandomAccessFile hdfsFile = new RandomAccessFile(hdfsSite, "rw");
    hdfsFile.setLength(hdfsFile.length() - 10);

    String coreSite = Paths.get(sTestDir.toPath().toString(), "core-site.xml").toString();
    ValidationTestUtils.writeXML(coreSite, ImmutableMap.of("key1", "value1"));
    RandomAccessFile coreFile = new RandomAccessFile(coreSite, "rw");
    coreFile.setLength(coreFile.length() - 10);

    sConf.set(PropertyKey.UNDERFS_HDFS_CONFIGURATION,
            hdfsSite + HdfsConfValidationTask.SEPARATOR + coreSite);
    HdfsConfValidationTask task =
            new HdfsConfValidationTask("hdfs://namenode:9000/alluxio", sConf);
    ValidationTaskResult result = task.loadHdfsConfig();
    assertEquals(ValidationUtils.State.FAILED, result.getState());
    assertThat(result.getResult(),
        containsString(String.format("Failed to parse %s", hdfsSite)));
    assertThat(result.getResult(), containsString(String.format("Failed to parse %s", coreSite)));
    assertThat(result.getAdvice(), containsString(String.format("Failed to parse %s", hdfsSite)));
    assertThat(result.getAdvice(), containsString(String.format("Failed to parse %s", coreSite)));
  }

}