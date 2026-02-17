class DummyClass_98303 {
  @Test
  public void testExceptionMessageWithResponseBody() {
    final URI uri = URI.create("http://example.com");
    final String responseBody = "uh oh";
    final DockerRequestException ex =
        new DockerRequestException("GET", uri, 500, responseBody, new RuntimeException());

    assertEquals(ex.getMessage(), "Request error: GET http://example.com: 500, body: uh oh");
  }

}