class DummyClass_98284 {
  @Test
  public void testFromDockerConfig_FullDockerCfg() throws Exception {
    final RegistryAuth registryAuth = RegistryAuth.fromDockerConfig(getTestFilePath(
        "dockerConfig/fullDockerCfg")).build();
    assertThat(registryAuth, equalTo(DOCKER_AUTH_CONFIG));
  }

}