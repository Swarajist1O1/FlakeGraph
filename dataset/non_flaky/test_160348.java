class DummyClass_160348 {
  @Test
  public void shouldReturnHTTPStatusOk() throws IOException {
    MetricsPublisherManager publisherManager =
        new MetricsPublisherManager(asyncRunnerFactory, timeProvider, metricsEndpoint);
    publisherManager.setMetricsPublisher(metricsPublisher);
    Assertions.assertThat(publisherManager.publishMetrics()).isEqualTo(200);
  }

}