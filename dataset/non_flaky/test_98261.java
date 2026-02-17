class DummyClass_98261 {
  @Test
  public void testPushHubPrivateImageWithAuth() throws Exception {
    // Push an image to a private repo on Docker Hub and check it succeeds
    final String dockerDirectory = Resources.getResource("dockerDirectory").getPath();
    final DockerClient client = DefaultDockerClient
        .fromEnv()
        .registryAuth(RegistryAuth.builder()
                        .username(HUB_AUTH_USERNAME)
                        .password(HUB_AUTH_PASSWORD)
                        .build())
        .build();

    client.build(Paths.get(dockerDirectory), HUB_PRIVATE_IMAGE);
    client.push(HUB_PRIVATE_IMAGE);
  }

}