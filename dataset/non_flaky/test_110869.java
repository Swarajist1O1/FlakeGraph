class DummyClass_110869 {
    @Test
    public void concurrentPutWithoutGlobalTransaction() throws InterruptedException {
        final int numberOfConcurrentPuts = ConcurrencyTesting.optimalTestThreadsCount();

        TestSubscriber<Changes> testSubscriber = new TestSubscriber<Changes>();

        storIOSQLite
                .observeChangesInTable(TweetTableMeta.TABLE)
                .subscribe(testSubscriber);

        final CountDownLatch concurrentPutLatch = new CountDownLatch(1);
        final CountDownLatch allPutsDoneLatch = new CountDownLatch(numberOfConcurrentPuts);

        for (int i = 0; i < numberOfConcurrentPuts; i++) {
            final int iCopy = i;

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        concurrentPutLatch.await();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    storIOSQLite
                            .put()
                            .object(Tweet.newInstance(null, 1L, "Some text: " + iCopy))
                            .prepare()
                            .executeAsBlocking();

                    allPutsDoneLatch.countDown();
                }

}