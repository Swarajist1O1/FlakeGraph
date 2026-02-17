class DummyClass_160349 {
  @Test
  public void shouldStopGracefully() throws IOException {
    MetricsPublisherManager publisherManager =
        new MetricsPublisherManager(asyncRunnerFactory, timeProvider, metricsEndpoint);
    publisherManager.setMetricsPublisher(metricsPublisher);
    SafeFuture<?> safeFuture = publisherManager.doStart();
    Assertions.assertThat(safeFuture).isEqualTo(SafeFuture.COMPLETE);
    safeFuture = publisherManager.doStop();
    Assertions.assertThat(safeFuture).isEqualTo(SafeFuture.COMPLETE);
  }

}