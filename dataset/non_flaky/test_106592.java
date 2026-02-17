class DummyClass_106592 {
  @Test
  public void wildcardProxyUsers() {
    String userName = System.getProperty("user.name");

    // Proxy users configured but not groups
    prepareHdfsConfFiles(ImmutableMap.of(
            String.format("hadoop.proxyuser.%s.users", userName), "*"));

    HdfsProxyUserValidationTask task =
            new HdfsProxyUserValidationTask("hdfs://namenode:9000/alluxio", mConf);
    ValidationTaskResult result = task.validateImpl(ImmutableMap.of());
    assertEquals(ValidationUtils.State.OK, result.getState());
  }

}