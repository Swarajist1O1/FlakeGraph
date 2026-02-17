class DummyClass_98334 {
  @Test
  public void testDockerCertificatesWithMultiCa() throws Exception {
    DockerCertificates.builder()
        .dockerCertPath(getCertPath())
        .caCertPath(getVariant("ca-multi.pem"))
        .sslFactory(factory)
        .build();

    verify(factory).newSslContext(keyStore.capture(), password.capture(), trustStore.capture());

    final KeyStore.PrivateKeyEntry pkEntry = (KeyStore.PrivateKeyEntry) keyStore.getValue()
        .getEntry("key", new KeyStore.PasswordProtection(password.getValue()));

    assertNotNull(pkEntry);
    assertNotNull(pkEntry.getCertificate());
    assertNotNull(trustStore.getValue().getCertificate(
        "cn=ca-test,o=internet widgits pty ltd,st=some-state,c=cr"));
    assertNotNull(trustStore.getValue().getCertificate(
        "cn=ca-test-2,o=internet widgits pty ltd,st=some-state,c=cr"));
  }

}