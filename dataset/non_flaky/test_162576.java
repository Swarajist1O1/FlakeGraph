class DummyClass_162576 {
  @Test
  public void shouldExtractCaseInsensitiveHeaders() {
    // given
    Map<String, String> headers =
        ImmutableMap.of(
            "X-b3-traceId",
            "4fd0b6131f19f39af59518d127b0cafe",
            "x-b3-spanid",
            "0000000000000456",
            "X-B3-Sampled",
            "true");

    // when
    Context context = ParentContextExtractor.extract(headers, INSTRUMENTER);
    // then
    Span span = Span.fromContext(context);
    SpanContext spanContext = span.getSpanContext();
    assertThat(spanContext.isValid()).isTrue();
    assertThat(spanContext.isValid()).isTrue();
    assertThat(spanContext.getSpanId()).isEqualTo("0000000000000456");
    assertThat(spanContext.getTraceId()).isEqualTo("4fd0b6131f19f39af59518d127b0cafe");
  }

}