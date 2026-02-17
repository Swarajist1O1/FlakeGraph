class DummyClass_98108 {
  @Test
  public void testSimpleAuthWithSource() {
    JsonObject config = new JsonObject();
    String username = TestUtils.randomAlphaString(8);
    String password = TestUtils.randomAlphaString(20);
    String authSource = TestUtils.randomAlphaString(10);
    config.put("username", username);
    config.put("password", password);
    config.put("authSource", authSource);

    List<MongoCredential> credentials = new CredentialListParser(null, config).credentials();
    assertEquals(1, credentials.size());
    MongoCredential credential = credentials.get(0);
    assertEquals(username, credential.getUserName());
    assertArrayEquals(password.toCharArray(), credential.getPassword());
    assertEquals(authSource, credential.getSource());
  }

}