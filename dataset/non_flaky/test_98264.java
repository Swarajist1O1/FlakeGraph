class DummyClass_98264 {
  @Test
  public void testPullHubPrivateRepoWithAuth() throws Exception {
    final RegistryAuth registryAuth = RegistryAuth.builder()
        .username(HUB_AUTH_USERNAME2)
        .password(HUB_AUTH_PASSWORD2)
        .build();
    client.pull("dxia2/scratch-private:latest", registryAuth);
  }

}