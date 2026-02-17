class DummyClass_162580 {
  @Test
  public void shouldCreateNoopRequestIfXrayPropagatorsSet() throws IOException {
    // given
    InputStream mock = mock(InputStream.class);
    GlobalOpenTelemetry.set(
        OpenTelemetry.propagating(ContextPropagators.create(AwsXrayPropagator.getInstance())));
    // when
    ApiGatewayProxyRequest created = ApiGatewayProxyRequest.forStream(mock);
    // then
    assertThat(created.freshStream()).isEqualTo(mock);
    assertThat(created.getHeaders()).isEmpty();
  }

}