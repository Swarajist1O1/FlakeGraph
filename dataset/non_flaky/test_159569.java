class DummyClass_159569 {
    @Test
    public void handlerThreadShouldReceiveNotification() throws ExecutionException, InterruptedException {
        final AssertionFailedError[] assertionFailedErrors = new AssertionFailedError[1];
        final CountDownLatch backgroundThreadReady = new CountDownLatch(1);
        final CountDownLatch numberOfInvocation = new CountDownLatch(1);

        HandlerThread handlerThread = new HandlerThread("handlerThread");
        handlerThread.start();
        Handler handler = new Handler(handlerThread.getLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                try {
                    assertEquals("handlerThread", Thread.currentThread().getName());
                } catch (AssertionFailedError e) {
                    assertionFailedErrors[0] = e;
                }
                final Realm backgroundRealm = Realm.getInstance(realmConfig);
                backgroundRealm.addChangeListener(new RealmChangeListener<Realm>() {
                    @Override
                    public void onChange(Realm object) {
                        backgroundRealm.close();
                        numberOfInvocation.countDown();
                    }

}