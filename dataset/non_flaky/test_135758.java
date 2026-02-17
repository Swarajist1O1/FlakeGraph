class DummyClass_135758 {
    @Test
    public void testMetricsPublisherReset() {
        // precondition: HystrixMetricsPublisherFactory class is not loaded. Calling HystrixPlugins.reset() here should be good enough to run this with other tests.

        // set first custom publisher
        HystrixCommandKey key = HystrixCommandKey.Factory.asKey("key");
        HystrixMetricsPublisherCommand firstCommand = new HystrixMetricsPublisherCommandDefault(key, null, null, null, null);
        HystrixMetricsPublisher firstPublisher = new CustomPublisher(firstCommand);
        HystrixPlugins.getInstance().registerMetricsPublisher(firstPublisher);

        // ensure that first custom publisher is used
        HystrixMetricsPublisherCommand cmd = HystrixMetricsPublisherFactory.createOrRetrievePublisherForCommand(key, null, null, null, null);
        assertSame(firstCommand, cmd);

        // reset, then change to second custom publisher
        HystrixPlugins.reset();
        HystrixMetricsPublisherCommand secondCommand = new HystrixMetricsPublisherCommandDefault(key, null, null, null, null);
        HystrixMetricsPublisher secondPublisher = new CustomPublisher(secondCommand);
        HystrixPlugins.getInstance().registerMetricsPublisher(secondPublisher);

        // ensure that second custom publisher is used
        cmd = HystrixMetricsPublisherFactory.createOrRetrievePublisherForCommand(key, null, null, null, null);
        assertNotSame(firstCommand, cmd);
        assertSame(secondCommand, cmd);
    }

}