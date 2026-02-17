class DummyClass_106575 {
  @Test
  public void versionNotMatched() throws Exception {
    PowerMockito.mockStatic(ShellUtils.class);
    String[] cmd = new String[]{"hadoop", "version"};
    BDDMockito.given(ShellUtils.execCommand(cmd)).willReturn("Hadoop 2.7");
    sConf.set(PropertyKey.UNDERFS_VERSION, "2.6");

    HdfsVersionValidationTask task = new HdfsVersionValidationTask(sConf);
    ValidationTaskResult result = task.validateImpl(ImmutableMap.of());
    assertEquals(ValidationUtils.State.FAILED, result.getState());
    assertThat(result.getResult(), containsString(
            "2.7 does not match alluxio.underfs.version=2.6"));
    assertThat(result.getAdvice(), containsString("configure alluxio.underfs.version"));
  }

}