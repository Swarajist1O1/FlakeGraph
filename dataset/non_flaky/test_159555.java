class DummyClass_159555 {
    @Test
    public void multiThread() throws InterruptedException, ExecutionException {
        final CountDownLatch workerCommittedLatch = new CountDownLatch(1);
        final CountDownLatch workerClosedLatch = new CountDownLatch(1);
        final CountDownLatch realmInMainClosedLatch = new CountDownLatch(1);
        final AssertionFailedError threadError[] = new AssertionFailedError[1];

        // Step 2.
        Thread workerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                Realm realm = Realm.getInstance(inMemConf);
                realm.beginTransaction();
                Dog dog = realm.createObject(Dog.class);
                dog.setName("DinoDog");
                realm.commitTransaction();

                try {
                    assertEquals(realm.where(Dog.class).count(), 1);
                } catch (AssertionFailedError afe) {
                    threadError[0] = afe;
                    realm.close();
                    return;
                }
                workerCommittedLatch.countDown();

                // Waits until Realm instance closed in main thread.
                try {
                    realmInMainClosedLatch.await(TestHelper.SHORT_WAIT_SECS, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    threadError[0] = new AssertionFailedError("Worker thread was interrupted.");
                    realm.close();
                    return;
                }

                realm.close();
                workerClosedLatch.countDown();
            }

}