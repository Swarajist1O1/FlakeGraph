class DummyClass_162577 {
  @Test
  public void shouldReadHeadersFromStream() {
    // given
    String json =
        "{"
            + "\"headers\" : {"
            + "\"X-B3-TraceId\": \"4fd0b6131f19f39af59518d127b0cafe\", \"X-B3-SpanId\": \"0000000000000456\", \"X-B3-Sampled\": \"true\""
            + "},"
            + "\"body\" : \"hello\""
            + "}";
    InputStream inputStream = new ByteArrayInputStream(json.getBytes(Charset.defaultCharset()));
    // when
    Map<String, String> headers = HeadersFactory.ofStream(inputStream);
    // then
    assertThat(headers).isNotNull();
    assertThat(headers.size()).isEqualTo(3);
    assertThat(headers)
        .containsOnly(
            entry("X-B3-TraceId", "4fd0b6131f19f39af59518d127b0cafe"),
            entry("X-B3-SpanId", "0000000000000456"),
            entry("X-B3-Sampled", "true"));
  }

}