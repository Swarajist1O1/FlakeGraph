class DummyClass_98106 {
  @Test
  public void testConnectionString() {
    String username = TestUtils.randomAlphaString(8);
    String password = TestUtils.randomAlphaString(20);

    ConnectionString connectionString = new ConnectionString(
      String.format(
        "mongodb://%s:%s@%s/%s",
        username,
        password,
        "localhost:27017",
        "my-datasource"));

    List<MongoCredential> credentials = new CredentialListParser(connectionString, null).credentials();
    assertEquals(1, credentials.size());
    MongoCredential credential = credentials.get(0);
    assertEquals(username, credential.getUserName());
    assertArrayEquals(password.toCharArray(), credential.getPassword());
    assertEquals("my-datasource", credential.getSource());
  }

}