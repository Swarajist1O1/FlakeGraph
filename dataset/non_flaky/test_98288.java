class DummyClass_98288 {
  @Test
  public void testFromDockerConfig_MissingConfigFile() throws Exception {
    final Path randomPath = Paths.get(RandomStringUtils.randomAlphanumeric(16) + ".json");
    expectedException.expect(FileNotFoundException.class);
    RegistryAuth.fromDockerConfig(randomPath).build();
  }

}