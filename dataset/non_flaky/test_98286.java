class DummyClass_98286 {
  @Test
  public void testFromDockerConfig_IncompleteConfig() throws Exception {
    final RegistryAuth registryAuth = RegistryAuth.fromDockerConfig(getTestFilePath(
        "dockerConfig/incompleteConfig.json")).build();
    assertThat(registryAuth, equalTo(EMPTY_AUTH_CONFIG));
  }

}