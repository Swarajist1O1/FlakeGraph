class DummyClass_98331 {
  @Test(expected = DockerCertificateException.class)
  public void testBadDockerCertificates() throws Exception {
    // try building a DockerCertificates with specifying a cert path to something that
    // isn't a cert
    DockerCertificates.builder()
        .dockerCertPath(getResourceFile("dockerInvalidSslDirectory"))
        .build();
  }

}