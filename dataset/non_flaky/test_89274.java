class DummyClass_89274 {
  @Test
  public void testStartShouldStartTheMetricsReportersAndServer() throws Exception {
    NetworkConnector connector = Mockito.mock(NetworkConnector.class);
    int testServerPort = 100;
    Mockito.doReturn(testServerPort).when(connector).getPort();
    Mockito.when(server.getConnectors()).thenReturn(new NetworkConnector[]{connector});
    Mockito.doNothing().when(server).start();
    samzaRestService.start();
    Mockito.verify(metricsReporter).start();
    Mockito.verify(metricsReporter).register("SamzaRest", metricsRegistry);
    Mockito.verify(server).start();
  }

}