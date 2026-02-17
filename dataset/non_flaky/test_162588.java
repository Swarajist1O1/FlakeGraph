class DummyClass_162588 {
  @Test
  public void shouldExtractHttpParentForHttpEndpoint() throws Exception {

    // given
    Endpoint endpoint = new HttpEndpoint("", new HttpComponent(), URI.create(""));
    Map<String, Object> exchangeHeaders =
        Collections.singletonMap(
            "uber-trace-id", "1f7f8dab3f0043b1b9cf0a75caf57510:a13825abcb764bd3:0:1");

    // when
    Context parent = CamelPropagationUtil.extractParent(exchangeHeaders, endpoint);

    // then
    Span parentSpan = Span.fromContext(parent);
    SpanContext parentSpanContext = parentSpan.getSpanContext();
    assertThat(parentSpanContext.getTraceId()).isEqualTo("1f7f8dab3f0043b1b9cf0a75caf57510");
    assertThat(parentSpanContext.getSpanId()).isEqualTo("a13825abcb764bd3");
  }

}