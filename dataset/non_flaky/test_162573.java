class DummyClass_162573 {
  @Test
  public void testSuccess() {
    AggregatedHttpResponse response = client.get(getAddress("hello/world")).aggregate().join();

    assertThat(response.status().code()).isEqualTo(200);
    assertThat(response.contentUtf8()).isEqualTo("hello world");

    testing.waitAndAssertTraces(
        trace ->
            trace.hasSpansSatisfyingExactly(
                span -> span.hasName("/hello/{name}").hasKind(SpanKind.SERVER).hasNoParent(),
                span ->
                    span.hasName("HelloController.hello")
                        .hasKind(SpanKind.INTERNAL)
                        .hasParent(trace.getSpan(0))));
  }

}