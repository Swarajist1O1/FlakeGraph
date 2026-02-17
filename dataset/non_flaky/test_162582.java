class DummyClass_162582 {
  @Test
  public void shouldUseCopyIfMarkingNotAvailableAndHttpPropagatorsSet() throws IOException {
    // given
    InputStream mock = mock(InputStream.class);
    given(mock.markSupported()).willReturn(false);
    given(mock.read(any(byte[].class))).willReturn(-1);
    GlobalOpenTelemetry.set(
        OpenTelemetry.propagating(ContextPropagators.create(B3Propagator.injectingSingleHeader())));
    // when
    ApiGatewayProxyRequest created = ApiGatewayProxyRequest.forStream(mock);
    // then
    assertThat(created.freshStream()).isInstanceOf(ByteArrayInputStream.class);
    then(mock).should(never()).mark(any(Integer.class));
    then(mock).should(never()).reset();
    then(mock).should().read(any());
  }

}