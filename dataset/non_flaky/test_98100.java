class DummyClass_98100 {
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidKey() throws IOException {
    // given
    final File tmpKeyFile = tmpFolder.newFile("brokenKey.pem");
    try (final FileWriter tmpKeyWriter = new FileWriter(tmpKeyFile)) {
      tmpKeyWriter.write("-----BEGIN CERTIFICATE-----\n" +
        "MIICljCCAfigAwIBAgI...BROKEN...xsykBBTOIVXnYdPkdZvvnoAIcfA7iM\n" +
        "-----END CERTIFICATE-----");
    }
    final File tmpCertFile = tmpFolder.newFile("validCert.pem");
    try (final FileWriter tmpCertWriter = new FileWriter(tmpCertFile)) {
      tmpCertWriter.write("-----BEGIN CERTIFICATE-----\n" +
        "MIICwTCCAamgAwIBAgIEBeVm4jANBgkqhkiG9w0BAQsFADARMQ8wDQYDVQQDEwZj\n" +
        "bGllbnQwHhcNMTgwNTI2MTEzNjUxWhcNMjEwNTI1MTEzNjUxWjARMQ8wDQYDVQQD\n" +
        "EwZjbGllbnQwggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIBAQDVmCecLdUZ\n" +
        "U917hweVz4JqvZ9vZEi1rH+BG98HYfRR/h3QaobxPImZu3hzKHZ+MPbm94HunLPA\n" +
        "VA9yZhvZMToNfOuD4TUPBPloBuNzwBfZk2O4CaXeG4ailVWUfm5t/l+RD/55zYKu\n" +
        "hw1/Vl9lcOryF2XAmPQ2F1gwEKK7wt1Ak8zw8/yeYgBv1/F+ibCMvR6FVj9ABBEf\n" +
        "TM+oOs4oy51otUv0h63GqYgXMJyLX7q+AGWdC3srwwLQROtkzi7y00g/YryXUoIq\n" +
        "dXEI7CrNL35rZXcZ5LfGRwFX9evX11PpT3OShYlsJBcFE9KMatRoIWd6xUKlxTk0\n" +
        "yLjoOUE2tsMJAgMBAAGjITAfMB0GA1UdDgQWBBQ6xJBQsJCJdj/u0iTLYYD2qQsB\n" +
        "DDANBgkqhkiG9w0BAQsFAAOCAQEAfoquV375+eAGmfnlLxB30v9VhsFckrxFVpYs\n" +
        "XXC6h2G8MtXLpIEpgJo+4SZ4YjNwf/8m9J5j/duU8RukYanyzJdgkFFqKDBYCX7U\n" +
        "SD1nQP7729KnQgxtbR/+i3zkNgo7FATdkLq+HOxklNOEE24Ldenya39bsG779B9n\n" +
        "Sskcbq++7rMM+onDYBv6PbUKCm6nfqPspq809CLxSaUJg9+9ykut6hiyke/i7GEP\n" +
        "XIZHrM+mEvG00ES/zBIdV6TE0AIBP7q2MN7ylT509Ko9sUBMOZdEzikYp5GaRdiv\n" +
        "zG9q6rqK5COK614BwJFOD1DKV1BoDFsgugvfvm/mrc3QfIUPDA==\n" +
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