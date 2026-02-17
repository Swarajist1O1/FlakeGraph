class DummyClass_135788 {
    @Test
    public void ensureThreadPoolInstanceIsTheOneRegisteredWithMetricsPublisherAndThreadPoolCache() throws IllegalAccessException, NoSuchFieldException {
        HystrixPlugins.getInstance().registerMetricsPublisher(new HystrixMetricsPublisher() {
            @Override
            public HystrixMetricsPublisherThreadPool getMetricsPublisherForThreadPool(HystrixThreadPoolKey threadPoolKey, HystrixThreadPoolMetrics metrics, HystrixThreadPoolProperties properties) {
                return new HystrixMetricsPublisherThreadPoolContainer(metrics);
            }

}