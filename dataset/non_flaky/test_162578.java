class DummyClass_162578 {
  @Test
  public void shouldReturnNullIfNoHeadersInStream() {
    // given
    String json = "{\"something\" : \"else\"}";
    InputStream inputStream = new ByteArrayInputStream(json.getBytes(Charset.defaultCharset()));
    // when
    Map<String, String> headers = HeadersFactory.ofStream(inputStream); // then
    assertThat(headers).isNull();
  }

}