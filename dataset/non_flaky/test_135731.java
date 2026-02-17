class DummyClass_135731 {
    @Test
    public void testCurrentConcurrentExecutionCount() throws InterruptedException {
        String key = "cmd-metrics-C";

        HystrixCommandMetrics metrics = null;
        List<Observable<Boolean>> cmdResults = new ArrayList<Observable<Boolean>>();

        int NUM_CMDS = 8;
        for (int i = 0; i < NUM_CMDS; i++) {
            HystrixCommand<Boolean> cmd = new SuccessCommand(key, 900);
            if (metrics == null) {
                metrics = cmd.metrics;
            }
            Observable<Boolean> eagerObservable = cmd.observe();
            cmdResults.add(eagerObservable);
        }

        try {
            Thread.sleep(150);
        } catch (InterruptedException ie) {
            fail(ie.getMessage());
        }
        System.out.println("ReqLog: " + HystrixRequestLog.getCurrentRequest().getExecutedCommandsAsString());
        assertEquals(NUM_CMDS, metrics.getCurrentConcurrentExecutionCount());

        final CountDownLatch latch = new CountDownLatch(1);
        Observable.merge(cmdResults).subscribe(new Subscriber<Boolean>() {
            @Override
            public void onCompleted() {
                System.out.println("All commands done");
                latch.countDown();
            }

}