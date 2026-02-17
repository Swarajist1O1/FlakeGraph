class DummyClass_162585 {
  @ParameterizedTest
  public void testHelloRequest(String path, String className) {
    AggregatedHttpResponse response = client.get(url.resolve(path).toString()).aggregate().join();

    assertThat(response.status().code()).isEqualTo(200);
    assertThat(response.contentUtf8()).isEqualTo("hello");

    testing.waitAndAssertTraces(
        trace ->
            trace.hasSpansSatisfyingExactly(
                span ->
                    span.hasName(getContextRoot() + path).hasKind(SpanKind.SERVER).hasNoParent(),
                span -> span.hasName(className + ".hello").hasParent(trace.getSpan(0))));
  }

}