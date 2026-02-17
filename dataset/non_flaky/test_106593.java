class DummyClass_106593 {
  @Test
  public void wildcardProxyGroups() {
    String userName = System.getProperty("user.name");

    // Proxy groups configured but not users
    prepareHdfsConfFiles(ImmutableMap.of(
            String.format("hadoop.proxyuser.%s.groups", userName), "*"));

    HdfsProxyUserValidationTask task =
            new HdfsProxyUserValidationTask("hdfs://namenode:9000/alluxio", mConf);
    ValidationTaskResult result = task.validateImpl(ImmutableMap.of());
    assertEquals(ValidationUtils.State.OK, result.getState());
  }

}