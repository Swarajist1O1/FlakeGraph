class DummyClass_98262 {
  @Test
  public void testPullHubPrivateRepoWithBadAuth() throws Exception {
    final RegistryAuth badRegistryAuth = RegistryAuth.builder()
        .username(HUB_AUTH_USERNAME2)
        .password("foobar")
        .build();
    exception.expect(DockerException.class);
    exception.expectCause(isA(NotAuthorizedException.class));
    client.pull(CIRROS_PRIVATE_LATEST, badRegistryAuth);
  }

}