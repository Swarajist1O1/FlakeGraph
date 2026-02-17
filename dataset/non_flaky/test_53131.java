class DummyClass_53131 {
    @Test
    public void requestCount_metrics_emitted() throws Exception {
        Mockito.when(metricsUtils.getUaaMetrics(any())).thenReturn(uaaMetrics1, uaaMetrics2);
        uaaMetricsEmitter.emitGlobalRequestMetrics();
        Mockito.verify(statsDClient).count("requests.global.completed.count", 3087l);
        Mockito.verify(statsDClient).gauge("requests.global.completed.time", 29l);
        Mockito.verify(statsDClient).count("requests.global.unhealthy.count", 1l);
        Mockito.verify(statsDClient).gauge("requests.global.unhealthy.time", 4318l);
        Mockito.verify(statsDClient).count("requests.global.status_1xx.count", 0l);
        Mockito.verify(statsDClient).count("requests.global.status_2xx.count", 2148l);
        Mockito.verify(statsDClient).count("requests.global.status_3xx.count", 763l);
        Mockito.verify(statsDClient).count("requests.global.status_4xx.count", 175l);
        Mockito.verify(statsDClient).count("requests.global.status_5xx.count", 1l);
        Mockito.verify(statsDClient).gauge("server.inflight.count", 3l);
        Mockito.verify(statsDClient).gauge("server.up.time", 12349843l);
        Mockito.verify(statsDClient).gauge("server.idle.time", 12349l);
        Mockito.verify(statsDClient).count("database.global.completed.count", 83797l);
        Mockito.verify(statsDClient).gauge("database.global.completed.time", 0l);
        Mockito.verify(statsDClient).count("database.global.unhealthy.count", 17549l);
        Mockito.verify(statsDClient).gauge("database.global.unhealthy.time", 0l);
        reset(statsDClient);
        uaaMetricsEmitter.emitGlobalRequestMetrics();
        Mockito.verify(statsDClient).count("requests.global.completed.count", 4l);
        Mockito.verify(statsDClient).count("requests.global.unhealthy.count", 1l);
        Mockito.verify(statsDClient).count("requests.global.status_1xx.count", 0l);
        Mockito.verify(statsDClient).count("requests.global.status_2xx.count", 1l);
        Mockito.verify(statsDClient).count("requests.global.status_3xx.count", 1l);
        Mockito.verify(statsDClient).count("requests.global.status_4xx.count", 1l);
        Mockito.verify(statsDClient).count("requests.global.status_5xx.count", 1l);
        Mockito.verify(statsDClient).count("database.global.completed.count", 2l);
        Mockito.verify(statsDClient).count("database.global.unhealthy.count", 5l);
        reset(statsDClient);
        uaaMetricsEmitter.emitGlobalRequestMetrics();
        Mockito.verify(statsDClient).count("requests.global.completed.count", 0l);
        Mockito.verify(statsDClient).count("requests.global.unhealthy.count", 0l);
        Mockito.verify(statsDClient).count("requests.global.status_1xx.count", 0l);
        Mockito.verify(statsDClient).count("requests.global.status_2xx.count", 0l);
        Mockito.verify(statsDClient).count("requests.global.status_3xx.count", 0l);
        Mockito.verify(statsDClient).count("requests.global.status_4xx.count", 0l);
        Mockito.verify(statsDClient).count("requests.global.status_5xx.count", 0l);
        Mockito.verify(statsDClient).count("database.global.completed.count", 0l);
        Mockito.verify(statsDClient).count("database.global.unhealthy.count", 0l);
    }

}