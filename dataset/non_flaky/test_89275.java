class DummyClass_89275 {
  @Test
  public void testStopShouldStopTheMetricsReportersAndStopTheServer() throws Exception {
    samzaRestService.stop();
    Mockito.verify(metricsReporter).stop();
    Mockito.verify(server).stop();
  }

}