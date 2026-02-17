class DummyClass_162579 {
  @Test
  public void shouldCreateNoopRequestIfNoPropagatorsSet() throws IOException {
    // given
    InputStream mock = mock(InputStream.class);
    GlobalOpenTelemetry.set(OpenTelemetry.noop());
    // when
    ApiGatewayProxyRequest created = ApiGatewayProxyRequest.forStream(mock);
    // then
    assertThat(created.freshStream()).isEqualTo(mock);
    assertThat(created.getHeaders()).isEmpty();
  }

}