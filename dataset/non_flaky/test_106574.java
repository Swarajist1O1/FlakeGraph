class DummyClass_106574 {
  @Test
  public void versionNotMatchedDefault() throws Exception {
    PowerMockito.mockStatic(ShellUtils.class);
    String[] cmd = new String[]{"hadoop", "version"};
    BDDMockito.given(ShellUtils.execCommand(cmd)).willReturn("Hadoop 2.2");

    HdfsVersionValidationTask task = new HdfsVersionValidationTask(sConf);
    ValidationTaskResult result = task.validateImpl(ImmutableMap.of());
    assertEquals(ValidationUtils.State.FAILED, result.getState());
    assertThat(result.getResult(), containsString("2.2 does not match alluxio.underfs.version"));
    assertThat(result.getAdvice(), containsString("configure alluxio.underfs.version"));
  }

}