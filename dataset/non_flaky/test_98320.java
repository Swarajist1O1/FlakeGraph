class DummyClass_98320 {
  @Test
  public void testEndpointFromEnv() throws Exception {
    when(systemDelegate.getenv("DOCKER_HOST")).thenReturn("foo", (String) null);
    when(systemDelegate.getProperty("os.name")).thenReturn("linux");
    DockerHost.setSystemDelegate(systemDelegate);

    assertThat(DockerHost.endpointFromEnv(), equalTo("foo"));
    assertThat(DockerHost.endpointFromEnv(), equalTo("unix:///var/run/docker.sock"));
  }

}