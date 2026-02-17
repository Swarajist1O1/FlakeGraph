class DummyClass_106588 {
  @Test
  public void skipped() {
    mConf.set(PropertyKey.SECURITY_AUTHENTICATION_TYPE, AuthType.NOSASL.getAuthName());
    HdfsProxyUserValidationTask task =
            new HdfsProxyUserValidationTask("hdfs://namenode:9000/alluxio", mConf);
    ValidationTaskResult result = task.validateImpl(ImmutableMap.of());
    assertEquals(ValidationUtils.State.SKIPPED, result.getState());
  }

}