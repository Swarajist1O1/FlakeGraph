class DummyClass_98096 {
  @Test
  public void testValidCaPemCertificate() throws IOException {
    // given
    final File tmpFile = tmpFolder.newFile("validCa.pem");
    try (final FileWriter tmpWriter = new FileWriter(tmpFile)) {
      tmpWriter.write("-----BEGIN CERTIFICATE-----\n" +
        "MIICljCCAfigAwIBAgIJAK0oe+f4DaojMAoGCCqGSM49BAMEMFkxCzAJBgNVBAYT\n" +
        "AkFUMQ8wDQYDVQQIDAZWaWVubmExDjAMBgNVBAoMBU5vRW52MSkwJwYDVQQLDCBO\n" +
        "b0VudiBSb290IENlcnRpZmljYXRlIEF1dGhvcml0eTAeFw0xNjEwMjcxNTAwNTFa\n" +
        "Fw00NjEwMjAxNTAwNTFaMFkxCzAJBgNVBAYTAkFUMQ8wDQYDVQQIDAZWaWVubmEx\n" +
        "DjAMBgNVBAoMBU5vRW52MSkwJwYDVQQLDCBOb0VudiBSb290IENlcnRpZmljYXRl\n" +
        "IEF1dGhvcml0eTCBmzAQBgcqhkjOPQIBBgUrgQQAIwOBhgAEAHpsMQth12N0d+aE\n" +
        "FIFRd8in4MTYZNSQEyQ4fuPDNq0Zb+4TXpUmedLZQJKkAQxorak8ESC/tXuQJDUL\n" +
        "OoKa+R6NAT4EKR1aaVVd7clC9rfGqVwGYslppycy9zsN6O4XLUiripamQF78FzRF\n" +
        "8wRZvkwYhzud+jpV6shgEMw3zmcwDSYKo2YwZDAdBgNVHQ4EFgQUD96n//91CReu\n" +
        "Cz1K0qics6aNFV0wHwYDVR0jBBgwFoAUD96n//91CReuCz1K0qics6aNFV0wEgYD\n" +
        "VR0TAQH/BAgwBgEB/wIBATAOBgNVHQ8BAf8EBAMCAYYwCgYIKoZIzj0EAwQDgYsA\n" +
        "MIGHAkFOxsApSB7fn8ZnYG/EUscn/uAkjxHsvdEkPKCC+XYCKMssW4YP2kR6gZjo\n" +
        "J8vaOAJZwNevBe/R9J8zMvsAWRJmWgJCAKLedGLnBuJOK9jjnKBwbVm5OIQfApMA\n" +
        "I2mJVnNXvS12w4DTZlP0K1t63WxsykBBTOIVXnYdPkdZvvnoAIcfA7iM\n" +
        "-----END CERTIFICATE-----");
    }
    final JsonObject withSSLAndCaPath = new JsonObject()
      .put("ssl", true)
      .put("caPath", tmpFile.getAbsolutePath());

    // when
    final SslSettings sslSettings = new MongoClientOptionsParser(vertx, withSSLAndCaPath)
      .settings()
      .getSslSettings();

    // then
    assertNotNull(sslSettings.getContext());
  }

}