class DummyClass_162581 {
  @Test
  public void shouldUseStreamMarkingIfHttpPropagatorsSet() throws IOException {
    // given
    InputStream mock = mock(InputStream.class);
    given(mock.markSupported()).willReturn(true);
    GlobalOpenTelemetry.set(
        OpenTelemetry.propagating(ContextPropagators.create(B3Propagator.injectingSingleHeader())));
    // when
    ApiGatewayProxyRequest created = ApiGatewayProxyRequest.forStream(mock);
    // then
    assertThat(created.freshStream()).isEqualTo(mock);
    then(mock).should(atLeastOnce()).mark(Integer.MAX_VALUE);
    then(mock).should().reset();
  }

}