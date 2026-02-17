class DummyClass_98335 {
  @Test
  public void testReadPrivateKeyPkcs1() throws Exception {
    DockerCertificates.builder()
        .dockerCertPath(getCertPath())
        .clientKeyPath(getVariant("key-pkcs1.pem"))
        .sslFactory(factory)
        .build();

    verify(factory).newSslContext(keyStore.capture(), password.capture(), trustStore.capture());

    final KeyStore.PrivateKeyEntry pkEntry = (KeyStore.PrivateKeyEntry) keyStore.getValue()
        .getEntry("key", new KeyStore.PasswordProtection(password.getValue()));

    assertNotNull(pkEntry.getPrivateKey());
  }

}