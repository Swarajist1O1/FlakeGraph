class DummyClass_98319 {
  @Test
  public void testDefaultDockerEndpoint() throws Exception {
    when(systemDelegate.getProperty("os.name")).thenReturn("linux", "mac", "other");
    DockerHost.setSystemDelegate(systemDelegate);

    assertThat(DockerHost.defaultDockerEndpoint(), equalTo("unix:///var/run/docker.sock"));
    assertThat(DockerHost.defaultDockerEndpoint(), equalTo("unix:///var/run/docker.sock"));
    assertThat(DockerHost.defaultDockerEndpoint(), equalTo("localhost:2375"));
  }

}