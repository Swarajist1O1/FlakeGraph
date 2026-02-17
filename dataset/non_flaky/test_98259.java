class DummyClass_98259 {
  @Test
  public void testPushImageToPrivateUnauthedRegistryWithAuth() throws Exception {
    registryContainerId = startUnauthedRegistry(client);

    // Push an image to the private registry and check it succeeds
    final String dockerDirectory = Resources.getResource("dockerDirectory").getPath();
    client.build(Paths.get(dockerDirectory), LOCAL_IMAGE);
    client.push(LOCAL_IMAGE);
    // We should be able to pull it again
    client.pull(LOCAL_IMAGE);
  }

}