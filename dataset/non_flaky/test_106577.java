class DummyClass_106577 {
  @Test
  public void minorVersionAccepted() throws Exception {
    PowerMockito.mockStatic(ShellUtils.class);
    String[] cmd = new String[]{"hadoop", "version"};
    // The minor version is not defined in Alluxio, which should work
    BDDMockito.given(ShellUtils.execCommand(cmd)).willReturn("Hadoop 2.6.2");
    sConf.set(PropertyKey.UNDERFS_VERSION, "2.6");

    HdfsVersionValidationTask task = new HdfsVersionValidationTask(sConf);
    ValidationTaskResult result = task.validateImpl(ImmutableMap.of());
    assertEquals(ValidationUtils.State.OK, result.getState());
  }

}