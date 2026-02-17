class DummyClass_98095 {
  @Test(expected = IllegalArgumentException.class)
  public void testEmptyCaPemCertificate() throws IOException {
    // given
    final File tmpFile = tmpFolder.newFile("invalidCa.pem");
    final JsonObject withSSLAndCaPath = new JsonObject()
      .put("ssl", true)
      .put("caPath", tmpFile.getAbsolutePath());

    // when
    final SslSettings sslSettings = new MongoClientOptionsParser(vertx, withSSLAndCaPath)
      .settings()
      .getSslSettings();

    // then
    assertNull(sslSettings.getContext());
  }

}