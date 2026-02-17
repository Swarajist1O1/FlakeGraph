class DummyClass_98258 {
  @Test
  public void testPushImageToPrivateUnauthedRegistryWithoutAuth() throws Exception {
    registryContainerId = startUnauthedRegistry(client);

    // Make a DockerClient without RegistryAuth
    final DefaultDockerClient client = DefaultDockerClient.fromEnv().build();

    // Push an image to the private registry and check it succeeds
    final String dockerDirectory = Resources.getResource("dockerDirectory").getPath();
    client.build(Paths.get(dockerDirectory), LOCAL_IMAGE);
    client.push(LOCAL_IMAGE);
    // We should be able to pull it again
    client.pull(LOCAL_IMAGE);
  }

}