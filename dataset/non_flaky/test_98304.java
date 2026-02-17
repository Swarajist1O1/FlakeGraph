class DummyClass_98304 {
  @Test
  public void testExceptionMessageWhenNoResponseBody() {
    final URI uri = URI.create("http://example.com");
    final String responseBody = null;
    final DockerRequestException ex =
        new DockerRequestException("GET", uri, 500, responseBody, new RuntimeException());

    assertEquals(ex.getMessage(), "Request error: GET http://example.com: 500");
  }

}