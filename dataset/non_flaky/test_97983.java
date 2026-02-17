class DummyClass_97983 {
    @Test
    public void testCache() throws InterruptedException {
        final AtomicInteger counter = new AtomicInteger();
        Observable<String> o = Observable.create(new OnSubscribeFunc<String>() {

            @Override
            public Subscription onSubscribe(final Observer<? super String> observer) {
                final BooleanSubscription subscription = new BooleanSubscription();
                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        counter.incrementAndGet();
                        observer.onNext("one");
                        observer.onCompleted();
                    }

}