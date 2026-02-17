class DummyClass_106590 {
  @Test
  public void proxyUserNotWildcard() {
    String userName = System.getProperty("user.name");

    // Configured proxy users and groups, but not wildcard
    String proxyUserKey = String.format("hadoop.proxyuser.%s.users", userName);
    String proxyGroupKey = String.format("hadoop.proxyuser.%s.groups", userName);
    prepareHdfsConfFiles(ImmutableMap.of(proxyUserKey, "user1,user2", proxyGroupKey, "groups"));

    HdfsProxyUserValidationTask task =
            new HdfsProxyUserValidationTask("hdfs://namenode:9000/alluxio", mConf);
    ValidationTaskResult result = task.validateImpl(ImmutableMap.of());
    assertEquals(ValidationUtils.State.WARNING, result.getState());
    assertThat(result.getResult(), containsString(
            String.format("%s=user1,user2 and %s=groups", proxyUserKey, proxyGroupKey)));
    assertThat(result.getAdvice(), containsString(
            "Please make sure that includes all users/groups Alluxio needs to impersonate as."));
  }

}