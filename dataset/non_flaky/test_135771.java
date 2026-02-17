class DummyClass_135771 {
    @Test
    public void testMetricsPublisherViaRegisterMethod() {
        HystrixPlugins.getInstance().registerMetricsPublisher(new HystrixMetricsPublisherTestImpl());
        HystrixMetricsPublisher impl = HystrixPlugins.getInstance().getMetricsPublisher();
        assertTrue(impl instanceof HystrixMetricsPublisherTestImpl);
    }

}