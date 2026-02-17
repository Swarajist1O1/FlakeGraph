class DummyClass_159568 {
    @Test
    public void looperThreadQuitsLooperEarly() throws InterruptedException {
        final CountDownLatch backgroundLooperStartedAndStopped = new CountDownLatch(1);
        final CountDownLatch mainThreadCommitCompleted = new CountDownLatch(1);
        final CountDownLatch backgroundThreadStopped = new CountDownLatch(1);

        // Starts background looper and let it hang.
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        //noinspection unused
        final Future<?> future = executorService.submit(new Runnable() {
            @Override
            public void run() {
                Looper.prepare(); // Fake background thread with a looper, eg. a IntentService.

                Realm realm = Realm.getInstance(realmConfig);
                realm.setAutoRefresh(false);
                TestHelper.quitLooperOrFail();
                backgroundLooperStartedAndStopped.countDown();
                // This will prevent backgroundThreadStopped from being called.
                TestHelper.awaitOrFail(mainThreadCommitCompleted);
                realm.close();
                backgroundThreadStopped.countDown();
            }

}