class DummyClass_135756 {
    @Test(timeout = 2500)
    public void testUnsubscribeWrappedScheduler() throws InterruptedException {
        Scheduler s = Schedulers.newThread();
        final AtomicBoolean interrupted = new AtomicBoolean();
        final CountDownLatch start = new CountDownLatch(1);
        final CountDownLatch end = new CountDownLatch(1);

        HystrixContextScheduler hcs = new HystrixContextScheduler(s);

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