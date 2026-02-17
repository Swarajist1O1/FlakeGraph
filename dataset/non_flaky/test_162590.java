class DummyClass_162590 {
  @Test
  public void shouldNotFailExtractingNullAwsParentForSqsEndpoint() {

    // given
    Endpoint endpoint = new SqsEndpoint("", new SqsComponent(), new SqsConfiguration());
    Map<String, Object> exchangeHeaders = Collections.singletonMap("AWSTraceHeader", null);

    // when
    Context parent = CamelPropagationUtil.extractParent(exchangeHeaders, endpoint);

    // then
    Span parentSpan = Span.fromContext(parent);
    SpanContext parentSpanContext = parentSpan.getSpanContext();
    assertThat(parentSpanContext.isValid()).isEqualTo(false);
  }

}