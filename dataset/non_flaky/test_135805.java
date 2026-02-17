class DummyClass_135805 {
    @Test
    public void testSemaphoreRejected() {
        HystrixCommandKey key = HystrixCommandKey.Factory.asKey("CMD-CumulativeCounter-H");
        stream = CumulativeCommandEventCounterStream.getInstance(key, 10, 500);
        stream.startCachingStreamValuesIfUnstarted();

        final CountDownLatch latch = new CountDownLatch(1);
        stream.observe().take(5).subscribe(getSubscriber(latch));

        //10 commands will saturate semaphore when called from different threads.
        //submit 2 more requests and they should be SEMAPHORE_REJECTED
        //should see 10 SUCCESSes, 2 SEMAPHORE_REJECTED and 2 FALLBACK_SUCCESSes

        List<Command> saturators = new ArrayList<Command>();

        for (int i = 0; i < 10; i++) {
            saturators.add(Command.from(groupKey, key, HystrixEventType.SUCCESS, 500, HystrixCommandProperties.ExecutionIsolationStrategy.SEMAPHORE));
        }

        Command rejected1 = Command.from(groupKey, key, HystrixEventType.SUCCESS, 0, HystrixCommandProperties.ExecutionIsolationStrategy.SEMAPHORE);
        Command rejected2 = Command.from(groupKey, key, HystrixEventType.SUCCESS, 0, HystrixCommandProperties.ExecutionIsolationStrategy.SEMAPHORE);

        for (final Command saturator : saturators) {
            new Thread(new HystrixContextRunnable(new Runnable() {
                @Override
                public void run() {
                    saturator.observe();
                }

}