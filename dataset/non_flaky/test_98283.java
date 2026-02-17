class DummyClass_98283 {
  @Test
  public void testFromDockerConfig_FullConfig() throws Exception {
    final RegistryAuth registryAuth = RegistryAuth.fromDockerConfig(getTestFilePath(
        "dockerConfig/fullConfig.json")).build();
    assertThat(registryAuth, equalTo(DOCKER_AUTH_CONFIG));
  }

}