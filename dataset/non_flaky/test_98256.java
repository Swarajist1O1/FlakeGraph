class DummyClass_98256 {
  @Test
  public void testPushImageToPrivateAuthedRegistryWithoutAuth() throws Exception {
    registryContainerId = startAuthedRegistry(client);

    // Make a DockerClient without RegistryAuth
    final DefaultDockerClient client = DefaultDockerClient.fromEnv().build();

    // Push an image to the private registry and check it fails
    final String dockerDirectory = Resources.getResource("dockerDirectory").getPath();
    client.build(Paths.get(dockerDirectory), LOCAL_IMAGE);

    exception.expect(ImagePushFailedException.class);
    client.push(LOCAL_IMAGE);
  }

}