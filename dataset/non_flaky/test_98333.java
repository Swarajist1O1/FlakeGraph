class DummyClass_98333 {
  @Test
  public void testDefaultDockerCertificates() throws Exception {
    DockerCertificates.builder()
        .dockerCertPath(getCertPath())
        .sslFactory(factory)
        .build();

    verify(factory).newSslContext(keyStore.capture(), password.capture(), trustStore.capture());

    final KeyStore.PrivateKeyEntry pkEntry = (KeyStore.PrivateKeyEntry) keyStore.getValue()
        .getEntry("key", new KeyStore.PasswordProtection(password.getValue()));

    final KeyStore caKeyStore = trustStore.getValue();

    assertNotNull(pkEntry);
    assertNotNull(pkEntry.getCertificate());
    assertNotNull(caKeyStore.getCertificate("o=boot2docker"));
  }

}