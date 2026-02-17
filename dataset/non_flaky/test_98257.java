class DummyClass_98257 {
  @Test
  public void testPushImageToPrivateAuthedRegistryWithAuth() throws Exception {
    registryContainerId = startAuthedRegistry(client);

    // Push an image to the private registry and check it succeeds
    final String dockerDirectory = Resources.getResource("dockerDirectory").getPath();
    client.build(Paths.get(dockerDirectory), LOCAL_IMAGE);
    client.tag(LOCAL_IMAGE, LOCAL_IMAGE_2);
    client.push(LOCAL_IMAGE);

    // Push the same image again under a different user
    final RegistryAuth registryAuth = RegistryAuth.builder()
        .username(LOCAL_AUTH_USERNAME_2)
        .password(LOCAL_AUTH_PASSWORD_2)
        .build();
    client.push(LOCAL_IMAGE_2, registryAuth);

    // We should be able to pull it again
    client.pull(LOCAL_IMAGE);
    client.pull(LOCAL_IMAGE_2);
  }

}