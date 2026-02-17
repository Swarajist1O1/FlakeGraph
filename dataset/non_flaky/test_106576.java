class DummyClass_106576 {
  @Test
  public void versionMatched() throws Exception {
    PowerMockito.mockStatic(ShellUtils.class);
    String[] cmd = new String[]{"hadoop", "version"};
    BDDMockito.given(ShellUtils.execCommand(cmd)).willReturn("Hadoop 2.6");
    sConf.set(PropertyKey.UNDERFS_VERSION, "2.6");

    HdfsVersionValidationTask task = new HdfsVersionValidationTask(sConf);
    ValidationTaskResult result = task.validateImpl(ImmutableMap.of());
    assertEquals(ValidationUtils.State.OK, result.getState());
  }

}