class DummyClass_98285 {
  @Test
  public void testFromDockerConfig_IdentityToken() throws Exception {
    final RegistryAuth authConfig = RegistryAuth.fromDockerConfig(getTestFilePath(
            "dockerConfig/identityTokenConfig.json")).build();
    assertThat(authConfig, equalTo(IDENTITY_TOKEN_AUTH_CONFIG));
  }

}