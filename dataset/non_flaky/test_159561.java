class DummyClass_159561 {
    @Test
    public void closeClearingHandlerMessages() throws InterruptedException, TimeoutException, ExecutionException {
        final int TEST_SIZE = 10;
        final CountDownLatch backgroundLooperStarted = new CountDownLatch(1);
        final CountDownLatch addHandlerMessages = new CountDownLatch(1);

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<Boolean> future = executorService.submit(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                Looper.prepare(); // Fake background thread with a looper, eg. a IntentService.
                Realm realm = Realm.getInstance(realmConfig);
                backgroundLooperStarted.countDown();

                // Random operation in the client code.
                final RealmResults<Dog> dogs = realm.where(Dog.class).findAll();
                if (dogs.size() != 0) {
                    return false;
                }
                // Wait for main thread to add update messages.
                addHandlerMessages.await(TestHelper.VERY_SHORT_WAIT_SECS, TimeUnit.SECONDS);

                // Creates a Handler for the thread now. All message and references for the notification handler will be
                // cleared once we call close().
                Handler threadHandler = new Handler(Looper.myLooper());
                realm.close(); // Close native resources + associated handlers.

                // Looper now reads the update message from the main thread if the Handler was not
                // cleared. This will cause an IllegalStateException and should not happen.
                // If it works correctly. The looper will just block on an empty message queue.
                // This is normal behavior but is bad for testing, so we add a custom quit message
                // at the end so we can evaluate results faster.
                // 500 ms delay is to make sure the notification daemon thread gets time to send notification.
                threadHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        TestHelper.quitLooperOrFail();
                    }

}