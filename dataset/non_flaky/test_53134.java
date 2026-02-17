class DummyClass_53134 {
    @Test
    public void perUrlGroup_request_metrics() throws Exception {
        Mockito.when(metricsUtils.getUaaMetrics(any())).thenReturn(uaaMetrics1);
        uaaMetricsEmitter.emitUrlGroupRequestMetrics();
        Mockito.verify(statsDClient).gauge(eq("requests.ui.completed.count"), gt(0l));
        Mockito.verify(statsDClient).gauge(eq("requests.ui.completed.time"), geq(300l));

        Mockito.verify(statsDClient).gauge(eq("requests.static-content.completed.count"), gt(0l));
        Mockito.verify(statsDClient).gauge(eq("requests.static-content.completed.time"), geq(23l));
    }

}