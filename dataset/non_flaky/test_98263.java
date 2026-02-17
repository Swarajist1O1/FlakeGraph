class DummyClass_98263 {
  @Test
  public void testBuildHubPrivateRepoWithAuth() throws Exception {
    final String dockerDirectory = Resources.getResource("dockerDirectoryNeedsAuth").getPath();
    final RegistryAuth registryAuth = RegistryAuth.builder()
        .username(HUB_AUTH_USERNAME2)
        .password(HUB_AUTH_PASSWORD2)
        .build();

    final DefaultDockerClient client = DefaultDockerClient.fromEnv()
        .registryAuth(registryAuth)
        .build();

    client.build(Paths.get(dockerDirectory), "testauth", BuildParam.pullNewerImage());
  }

}