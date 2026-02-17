class DummyClass_135772 {
    @Test
    public void testMetricsPublisherViaProperty() {
        try {
            String fullClass = HystrixMetricsPublisherTestImpl.class.getName();
            System.setProperty("hystrix.plugin.HystrixMetricsPublisher.implementation", fullClass);
            HystrixMetricsPublisher impl = HystrixPlugins.getInstance().getMetricsPublisher();
            assertTrue(impl instanceof HystrixMetricsPublisherTestImpl);
        } finally {
            System.clearProperty("hystrix.plugin.HystrixMetricsPublisher.implementation");
        }
	}*/

}