class DummyClass_98324 {
  @Test
  public void testPortFromEnv() throws Exception {
    when(systemDelegate.getenv("DOCKER_PORT")).thenReturn("1234", (String) null);
    DockerHost.setSystemDelegate(systemDelegate);

    assertThat(DockerHost.portFromEnv(), equalTo(1234));
    assertThat(DockerHost.portFromEnv(), equalTo(2375));
  }

}