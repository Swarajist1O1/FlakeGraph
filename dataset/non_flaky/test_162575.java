class DummyClass_162575 {
  @Test
  public void shouldPreferAwsParentHeaderIfValidAndSampled() {
    // given
    Map<String, String> headers =
        ImmutableMap.of(
            "X-b3-traceId",
            "4fd0b6131f19f39af59518d127b0cafe",
            "x-b3-spanid",
            "0000000000000456",
            "X-B3-Sampled",
            "true");
    environmentVariables.set(
        "_X_AMZN_TRACE_ID",
        "Root=1-8a3c60f7-d188f8fa79d48a391a778fa6;Parent=0000000000000456;Sampled=1");

    // when
    Context context = ParentContextExtractor.extract(headers, INSTRUMENTER);
    // then
    Span span = Span.fromContext(context);
    SpanContext spanContext = span.getSpanContext();
    assertThat(spanContext.isValid()).isTrue();
    assertThat(spanContext.isValid()).isTrue();
    assertThat(spanContext.getSpanId()).isEqualTo("0000000000000456");
    assertThat(spanContext.getTraceId()).isEqualTo("8a3c60f7d188f8fa79d48a391a778fa6");
  }

}