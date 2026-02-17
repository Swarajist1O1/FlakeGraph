class DummyClass_106589 {
  @Test
  public void missingProxyUser() {
    String userName = System.getProperty("user.name");

    // No proxy user definition in core-site.xml
    prepareHdfsConfFiles(ImmutableMap.of("key1", "value1"));

    HdfsProxyUserValidationTask task =
            new HdfsProxyUserValidationTask("hdfs://namenode:9000/alluxio", mConf);
    ValidationTaskResult result = task.validateImpl(ImmutableMap.of());
    assertEquals(ValidationUtils.State.FAILED, result.getState());
    assertThat(result.getResult(), containsString(
            "Alluxio is not able to perform impersonation."));
    assertThat(result.getAdvice(), containsString(
            String.format("Please enable Alluxio user %s to impersonate", userName)));
  }

}