class DummyClass_94602 {
  @Test
  public void setInstanceFollowRedirectsFalse() throws Exception {
    server.enqueue(new MockResponse()
        .setResponseCode(302)
        .addHeader("Location: /b")
        .setBody("A"));
    server.enqueue(new MockResponse()
        .setBody("B"));

    HttpURLConnection connection = factory.open(server.url("/a").url());
    connection.setInstanceFollowRedirects(false);
    assertResponseBody(connection, "A");
    assertResponseCode(connection, 302);
  }

}