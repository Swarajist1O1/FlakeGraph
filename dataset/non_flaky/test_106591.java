class DummyClass_106591 {
  @Test
  public void proxyUsersAndGroupsAllMissing() {
    String userName = System.getProperty("user.name");

    // Proxyuser configured for bob, not the running user
    prepareHdfsConfFiles(ImmutableMap.of("hadoop.proxyuser.bob.users", "user1,user3",
            "hadoop.proxyuser.bob.groups", "*"));

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