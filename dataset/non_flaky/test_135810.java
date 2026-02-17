class DummyClass_135810 {
    @Test
    public void testCancelled() {
        HystrixCommandKey key = HystrixCommandKey.Factory.asKey("CMD-CumulativeCounter-M");
        stream = CumulativeCommandEventCounterStream.getInstance(key, 10, 500);
        stream.startCachingStreamValuesIfUnstarted();

        final CountDownLatch latch = new CountDownLatch(1);
        stream.observe().take(5).subscribe(getSubscriber(latch));

        Command toCancel = Command.from(groupKey, key, HystrixEventType.SUCCESS, 500);

        System.out.println(System.currentTimeMillis() + " : " + Thread.currentThread().getName() + " : about to observe and subscribe");
        Subscription s = toCancel.observe().
                doOnUnsubscribe(new Action0() {
                    @Override
                    public void call() {
                        System.out.println(System.currentTimeMillis() + " : " + Thread.currentThread().getName() + " : UnSubscribe from command.observe()");
                    }

}