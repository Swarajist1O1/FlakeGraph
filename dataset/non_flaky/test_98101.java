class DummyClass_98101 {
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidCertificate() throws IOException {
    // given
    final File tmpKeyFile = tmpFolder.newFile("validKey.pem");
    try (final FileWriter tmpKeyWriter = new FileWriter(tmpKeyFile)) {
      tmpKeyWriter.write("-----BEGIN PRIVATE KEY-----\n" +
        "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDVmCecLdUZU917\n" +
        "hweVz4JqvZ9vZEi1rH+BG98HYfRR/h3QaobxPImZu3hzKHZ+MPbm94HunLPAVA9y\n" +
        "ZhvZMToNfOuD4TUPBPloBuNzwBfZk2O4CaXeG4ailVWUfm5t/l+RD/55zYKuhw1/\n" +
        "Vl9lcOryF2XAmPQ2F1gwEKK7wt1Ak8zw8/yeYgBv1/F+ibCMvR6FVj9ABBEfTM+o\n" +
        "Os4oy51otUv0h63GqYgXMJyLX7q+AGWdC3srwwLQROtkzi7y00g/YryXUoIqdXEI\n" +
        "7CrNL35rZXcZ5LfGRwFX9evX11PpT3OShYlsJBcFE9KMatRoIWd6xUKlxTk0yLjo\n" +
        "OUE2tsMJAgMBAAECggEAdewZAjqzidYpU0eLQoRcBj5GRaNiGRrxEgCnM1Y7IwFe\n" +
        "yG/nrEu11DASIdHXCXhS99Tx4SCWhLpkBM6m1VQ+LrAm/ppZRr+CSpJzBLaq9C5R\n" +
        "QYviDSu5Ow2jP+ZFZWiorlfcMLbrTRu2sfSnmkOrEpkkTh6jxTFCONcWYP8GU93D\n" +
        "YCA3hSH0li7CueS+GYJ1JB2Cd7buu+tOhl36AhBD96miExlgNn0YGpTJJ3I0Hb+O\n" +
        "lKIIQy+KK8f9TXrSeZC3OYlTtJaIr9ejspTXxIYN11EIit5MFEwnnkCglcsePjsx\n" +
        "qeOFRumJ5Nj5H8qyCNZ5MtzwbLkyktJzlumvnyr+AQKBgQDv/QfGKZJFeoCEWpoj\n" +
        "f+078JxSYyPVNXxbbr2NuN/V79hJBol87ukycz2CZkDCubIKfubc50eXDmhWCp4p\n" +
        "aJgl6BMhnovftYrIrGWJLwqXnwFwsKJSrJJqHlHDJDRGfUSQEWNclNeaB3Mr8W46\n" +
        "Zcaadeikstvka9xKA1LOCG3oIQKBgQDj2FFOxZK27KhY/9Oz1dUsPtAYYbLOor/P\n" +
        "Rbne3jICQStH3dnUEmWKIKrdYV1u2saw5djn3ujwB0xEXydRvRgiSF0qxYjbm9CG\n" +
        "TJaiHhTsQDjWkYMZaxk3gc7Yfh8DHF0wlvWpu1wMXNsCJ6jxqW2e+jSRioZICPK6\n" +
        "McWWmArd6QKBgDWjoHEyKXdOAhuTBJCarzOOe+IONpwY8EqfXc6nW6A9k2H/DAvY\n" +
        "elbEWyMiJ6deSeT+qCsHpoCkv707ck5fCmKulFgXT7wYn4Rqw+b9lKh+6Zt+X0mL\n" +
        "OM5vKGctWGHI7eIlgMfYnLfYom1X8QMsbE9puy3UrEFJulrwkzlpuOcBAoGAVRNV\n" +
        "sNsXIFSXu7uyueizU3UU0LXSRVQB2QxJDg3bkHnzBj+xcX15Cq2N/2G2uIjaPf1l\n" +
        "E5dpVQ70jGcXUG8SDuMEXs8pfg7dOvhoGpqu51RHpN7qm9ggr1g5+x6Ex+2UYmtL\n" +
        "yZfbFAasBE74x1ujQgRdEqct4sHsmFezVrro+9kCgYEAgl70mKk9yK/f7515OaO0\n" +
        "Y39tgVzpAG6RN1NKnY6NR5VNNemZx5jhKfk5byaYxX4XBjygD0sQ5KTpaZmoQIIX\n" +
        "FxuwhLRRMn6vtsEf1HexJAtRd82aL5wKS62l0AXG/CVLAygn4aSSqLrgTyFFVUR3\n" +
        "cASPpPIdZaKZG6q4Hmcpl58=\n" +
        "-----END PRIVATE KEY-----");
    }
    final File tmpCertFile = tmpFolder.newFile("brokenCert.pem");
    try (final FileWriter tmpCertWriter = new FileWriter(tmpCertFile)) {
      tmpCertWriter.write("-----BEGIN CERTIFICATE-----\n" +
        "MIICwTCCAamgAwIBA...BROKEN...FOD1DKV1BoDFsgugvfvm/mrc3QfIUPDA==\n" +
        "-----END CERTIFICATE-----");
    }
    final JsonObject withSSLAndCertKeyPath = new JsonObject()
      .put("ssl", true)
      .put("keyPath", tmpKeyFile.getAbsolutePath())
      .put("certPath", tmpCertFile.getAbsolutePath());

    // then
    new MongoClientOptionsParser(vertx, withSSLAndCertKeyPath);
  }

}