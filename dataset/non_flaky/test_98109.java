class DummyClass_98109 {
  @Test
  public void testAuth_GSSAPI() {
    JsonObject config = new JsonObject();
    String username = TestUtils.randomAlphaString(8);
    String authSource = TestUtils.randomAlphaString(10);
    config.put("username", username);
    config.put("authSource", authSource);
    config.put("authMechanism", "GSSAPI");

    List<MongoCredential> credentials = new CredentialListParser(null, config).credentials();
    assertEquals(1, credentials.size());
    MongoCredential credential = credentials.get(0);
    assertEquals(username, credential.getUserName());
    assertNotEquals(authSource, credential.getSource()); // It should ignore the source we pass in

    assertEquals(AuthenticationMechanism.GSSAPI, credential.getAuthenticationMechanism());
  }

}