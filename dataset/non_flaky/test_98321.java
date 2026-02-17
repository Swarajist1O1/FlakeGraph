class DummyClass_98321 {
  @Test
  public void testDefaultUnixEndpoint() throws Exception {
    assertThat(DockerHost.defaultUnixEndpoint(), equalTo("unix:///var/run/docker.sock"));
  }

}