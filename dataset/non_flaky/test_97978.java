class DummyClass_97978 {
    @Test
    public void testCustomObservableWithErrorInObserverAsynchronous() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);
        final AtomicInteger count = new AtomicInteger();
        final AtomicReference<Throwable> error = new AtomicReference<Throwable>();
        Observable.create(new OnSubscribeFunc<String>() {

            @Override
            public Subscription onSubscribe(final Observer<? super String> observer) {
                final BooleanSubscription s = new BooleanSubscription();
                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        try {
                            if (!s.isUnsubscribed()) {
                                observer.onNext("1");
                                observer.onNext("2");
                                observer.onNext("three");
                                observer.onNext("4");
                                observer.onCompleted();
                            }
                        } finally {
                            latch.countDown();
                        }
                    }

}