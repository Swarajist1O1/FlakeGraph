class DummyClass_98326 {
  @Test
  public void testCertPathFromEnv() throws Exception {
    when(systemDelegate.getenv("DOCKER_CERT_PATH")).thenReturn("foo", (String) null);
    when(systemDelegate.getProperty("user.home")).thenReturn("bar");
    DockerHost.setSystemDelegate(systemDelegate);

    assertThat(DockerHost.certPathFromEnv(), equalTo("foo"));
    assertThat(DockerHost.certPathFromEnv(), nullValue());
  }

}