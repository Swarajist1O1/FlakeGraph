class DummyClass_53137 {
    @Test
    public void auditService_metricValues_areNull() throws Exception {
        mBeanMap1.put("user_authentication_count", null);
        Mockito.when(metricsUtils.pullUpMap("cloudfoundry.identity", "*", server)).thenReturn((Map)mBeanMap2);
        uaaMetricsEmitter.emitMetrics();
        Mockito.verify(statsDClient).gauge("audit_service.user_not_found_count", 1);
        Mockito.verify(statsDClient, times(6)).gauge(anyString(), anyLong());
    }

}