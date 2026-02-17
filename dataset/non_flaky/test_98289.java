class DummyClass_98289 {
  @Test
  public void testFromDockerConfig_MultiConfig() throws Exception {
    final RegistryAuth myDockParsed = RegistryAuth.fromDockerConfig(getTestFilePath(
        "dockerConfig/multiConfig.json"), "https://narnia.mydock.io/v1/").build();
    assertThat(myDockParsed, equalTo(MY_AUTH_CONFIG));
    final RegistryAuth dockerIoParsed = RegistryAuth.fromDockerConfig(getTestFilePath(
        "dockerConfig/multiConfig.json"), "https://index.docker.io/v1/").build();
    assertThat(dockerIoParsed, equalTo(DOCKER_AUTH_CONFIG));
  }

}