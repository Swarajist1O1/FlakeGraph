class DummyClass_98098 {
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidPemCertificate() throws IOException {
    // given
    final File tmpFile = tmpFolder.newFile("brokenCa.pem");
    try (final FileWriter tmpWriter = new FileWriter(tmpFile)) {
      tmpWriter.write("-----BEGIN CERTIFICATE-----\n" +
        "MIICljCCAfigAwIBAgI...BROKEN...xsykBBTOIVXnYdPkdZvvnoAIcfA7iM\n" +
        "-----END CERTIFICATE-----");
    }
    final JsonObject withSSLAndCaPath = new JsonObject()
      .put("ssl", true)
      .put("caPath", tmpFile.getAbsolutePath());

    // then
    new MongoClientOptionsParser(vertx, withSSLAndCaPath);
  }

}