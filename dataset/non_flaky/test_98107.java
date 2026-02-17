class DummyClass_98107 {
  @Test
  public void testSimpleAuth() {
    JsonObject config = new JsonObject().put("db_name", "my-datasource");
    String username = TestUtils.randomAlphaString(8);
    String password = TestUtils.randomAlphaString(20);
    config.put("username", username);
    config.put("password", password);


    List<MongoCredential> credentials = new CredentialListParser(null, config).credentials();
    assertEquals(1, credentials.size());
    MongoCredential credential = credentials.get(0);
    assertEquals(username, credential.getUserName());
    assertArrayEquals(password.toCharArray(), credential.getPassword());
    // default source should be the database name - see https://github.com/vert-x3/vertx-mongo-client/issues/46.
    assertEquals("my-datasource", credential.getSource());
  }

}