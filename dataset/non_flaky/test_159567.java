class DummyClass_159567 {
    @Test
    public void doNotUseClosedHandler() throws InterruptedException {
        final CountDownLatch handlerNotified = new CountDownLatch(1);
        final CountDownLatch backgroundThread1Started = new CountDownLatch(1);
        final CountDownLatch backgroundThread2Closed = new CountDownLatch(1);

        // Creates Handler on Thread1 by opening a Realm instance.
        new Thread("thread1") {

            @Override
            public void run() {
                Looper.prepare();
                final Realm realm = Realm.getInstance(realmConfig);
                RealmChangeListener<Realm> listener = new RealmChangeListener<Realm>() {
                    @Override
                    public void onChange(Realm object) {
                        realm.close();
                        handlerNotified.countDown();
                    }

}