class DummyClass_135789 {
    @Test(timeout = 2500)
    public void testUnsubscribeHystrixThreadPool() throws InterruptedException {
        // methods are package-private so can't test it somewhere else
        HystrixThreadPool pool = Factory.getInstance(HystrixThreadPoolKey.Factory.asKey("threadPoolFactoryTest"),
                HystrixThreadPoolProperties.Setter.getUnitTestPropertiesBuilder());
        
        final AtomicBoolean interrupted = new AtomicBoolean();
        final CountDownLatch start = new CountDownLatch(1);
        final CountDownLatch end = new CountDownLatch(1);

        HystrixContextScheduler hcs = new HystrixContextScheduler(HystrixPlugins.getInstance().getConcurrencyStrategy(), pool);

        Scheduler.Worker w = hcs.createWorker();

        try {
            w.schedule(new Action0() {
                @Override
                public void call() {
                    start.countDown();
                    try {
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException ex) {
                            interrupted.set(true);
                        }
                    } finally {
                        end.countDown();
                    }
                }

}