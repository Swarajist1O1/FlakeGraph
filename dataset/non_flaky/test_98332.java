class DummyClass_98332 {
  @Test
  public void testNoDockerCertificatesInDir() throws Exception {
    final Path certDir = Paths.get(System.getProperty("java.io.tmpdir"));
    final Optional<DockerCertificatesStore> result = DockerCertificates.builder()
        .dockerCertPath(certDir)
        .build();
    assertThat(result.isPresent(), is(false));
  }

}