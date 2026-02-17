class DummyClass_162591 {
  @Test
  public void shouldExtractAwsParentForSqsEndpoint() {

    // given
    Endpoint endpoint = new SqsEndpoint("", new SqsComponent(), new SqsConfiguration());
    Map<String, Object> exchangeHeaders =
        Collections.singletonMap(
            "AWSTraceHeader",
            "Root=1-5759e988-bd862e3fe1be46a994272793;Parent=53995c3f42cd8ad8;Sampled=1\n");

    // when
    Context parent = CamelPropagationUtil.extractParent(exchangeHeaders, endpoint);

    // then
    Span parentSpan = Span.fromContext(parent);
    SpanContext parentSpanContext = parentSpan.getSpanContext();
    assertThat(parentSpanContext.getTraceId()).isEqualTo("5759e988bd862e3fe1be46a994272793");
    assertThat(parentSpanContext.getSpanId()).isEqualTo("53995c3f42cd8ad8");
  }

}