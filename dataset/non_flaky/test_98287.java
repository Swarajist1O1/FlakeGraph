class DummyClass_98287 {
  @Test
  public void testFromDockerConfig_WrongConfigs() throws Exception {
    final RegistryAuth registryAuth1 = RegistryAuth.fromDockerConfig(getTestFilePath(
        "dockerConfig/wrongConfig1.json")).build();
    assertThat(registryAuth1, equalTo(EMPTY_AUTH_CONFIG));

    final RegistryAuth registryAuth2 = RegistryAuth.fromDockerConfig(getTestFilePath(
        "dockerConfig/wrongConfig2.json")).build();
    assertThat(registryAuth2, equalTo(EMPTY_AUTH_CONFIG));
  }

}