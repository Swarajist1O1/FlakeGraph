class DummyClass_135757 {
    @Test
    public void testSingleInitializePerKey() {
        final TestHystrixMetricsPublisher publisher = new TestHystrixMetricsPublisher();
        HystrixPlugins.getInstance().registerMetricsPublisher(publisher);
        final HystrixMetricsPublisherFactory factory = new HystrixMetricsPublisherFactory();
        ArrayList<Thread> threads = new ArrayList<Thread>();
        for (int i = 0; i < 20; i++) {
            threads.add(new Thread(new Runnable() {

                @Override
                public void run() {
                    factory.getPublisherForCommand(TestCommandKey.TEST_A, null, null, null, null);
                    factory.getPublisherForCommand(TestCommandKey.TEST_B, null, null, null, null);
                    factory.getPublisherForThreadPool(TestThreadPoolKey.TEST_A, null, null);
                }

}