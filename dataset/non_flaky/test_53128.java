class DummyClass_53128 {
    @Test
    public void emittingMetrics_Is_Scheduled() throws Exception {
        Scheduled schedulerAnnotation = uaaMetricsEmitter.getClass().getMethod("emitMetrics").getAnnotation(Scheduled.class);
        Assert.assertEquals(5000, schedulerAnnotation.fixedRate());
    }

}