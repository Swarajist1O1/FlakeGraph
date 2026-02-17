class DummyClass_98260 {
  @Test
  public void testPushHubPublicImageWithAuth() throws Exception {
    // Push an image to a public repo on Docker Hub and check it succeeds
    final String dockerDirectory = Resources.getResource("dockerDirectory").getPath();
    final DockerClient client = DefaultDockerClient
        .fromEnv()
        .registryAuth(RegistryAuth.builder()
                        .username(HUB_AUTH_USERNAME)
                        .password(HUB_AUTH_PASSWORD)
                        .build())
        .build();

    client.build(Paths.get(dockerDirectory), HUB_PUBLIC_IMAGE);
    client.push(HUB_PUBLIC_IMAGE);
  }

}