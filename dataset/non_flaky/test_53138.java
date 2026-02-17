class DummyClass_53138 {
    @Test
    public void auditService_Key_isNull () throws Exception {
        mBeanMap2.put("UaaAudit", null);
        Mockito.when(metricsUtils.pullUpMap("cloudfoundry.identity", "*", server)).thenReturn((Map)mBeanMap2);
        uaaMetricsEmitter.emitMetrics();
        Mockito.verify(statsDClient, times(0)).gauge(anyString(), anyLong());
    }

}