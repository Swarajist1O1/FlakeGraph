class DummyClass_175757 {
  @Test(expected = NullPointerException.class)
  public void testNullCredential() {
    new GcpProjectQueryJob(null /* credential */, projectRepository, projectSelector,
        dataBindingContext, isLatestQueryJob);
  }

}