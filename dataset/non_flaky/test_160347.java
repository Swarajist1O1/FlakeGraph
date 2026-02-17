class DummyClass_160347 {
  @Test
  public void shouldRunPublisherEveryXSeconds() throws InterruptedException, IOException {
    MetricsPublisherManager publisherManager =
        new MetricsPublisherManager(asyncRunnerFactory, timeProvider, metricsEndpoint);
    publisherManager.setMetricsPublisher(metricsPublisher);
    verify(metricsPublisher, times(0)).publishMetrics(anyString(), anyString());
    SafeFuture<?> safeFuture = publisherManager.doStart();
    assertThat(asyncRunnerFactory.getStubAsyncRunners().size()).isEqualTo(1);
    asyncRunnerFactory.getStubAsyncRunners().get(0).executeQueuedActions();
    verify(metricsPublisher, times(1)).publishMetrics(anyString(), anyString());
    asyncRunnerFactory.getStubAsyncRunners().get(0).executeQueuedActions();
    verify(metricsPublisher, times(2)).publishMetrics(anyString(), anyString());
    Assertions.assertThat(safeFuture).isEqualTo(SafeFuture.COMPLETE);
  }

}