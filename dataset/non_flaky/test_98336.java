class DummyClass_98336 {
  @Test
  public void testReadPrivateKeyPkcs8() throws Exception {
    DockerCertificates.builder()
        .dockerCertPath(getCertPath())
        .clientKeyPath(getVariant("key-pkcs8.pem"))
        .sslFactory(factory)
        .build();

    verify(factory).newSslContext(keyStore.capture(), password.capture(), trustStore.capture());

    final KeyStore.PrivateKeyEntry pkEntry = (KeyStore.PrivateKeyEntry) keyStore.getValue()
        .getEntry("key", new KeyStore.PasswordProtection(password.getValue()));

    assertNotNull(pkEntry.getPrivateKey());
  }

}