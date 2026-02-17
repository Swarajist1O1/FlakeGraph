class DummyClass_106578 {
  @Test
  public void minorVersionConflict() throws Exception {
    PowerMockito.mockStatic(ShellUtils.class);
    String[] cmd = new String[]{"hadoop", "version"};
    // Alluxio defines a different minor version, which should not work
    BDDMockito.given(ShellUtils.execCommand(cmd)).willReturn("Hadoop 2.6.2");
    sConf.set(PropertyKey.UNDERFS_VERSION, "2.6.3");

    HdfsVersionValidationTask task = new HdfsVersionValidationTask(sConf);
    ValidationTaskResult result = task.validateImpl(ImmutableMap.of());
    assertEquals(ValidationUtils.State.FAILED, result.getState());
    assertThat(result.getResult(), containsString(
            "Hadoop version 2.6.2 does not match alluxio.underfs.version=2.6.3"));
  }

}