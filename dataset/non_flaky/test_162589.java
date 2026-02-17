class DummyClass_162589 {
  @Test
  public void shouldNotFailExtractingNullHttpParentForHttpEndpoint() throws Exception {

    // given
    Endpoint endpoint = new HttpEndpoint("", new HttpComponent(), URI.create(""));
    Map<String, Object> exchangeHeaders = Collections.singletonMap("uber-trace-id", null);

    // when
    Context parent = CamelPropagationUtil.extractParent(exchangeHeaders, endpoint);

    // then
    Span parentSpan = Span.fromContext(parent);
    SpanContext parentSpanContext = parentSpan.getSpanContext();
    assertThat(parentSpanContext.isValid()).isEqualTo(false);
  }

}