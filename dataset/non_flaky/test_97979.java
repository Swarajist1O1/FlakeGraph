class DummyClass_97979 {
    @Test
    public void testCustomObservableWithErrorInObserverSynchronous() {
        final AtomicInteger count = new AtomicInteger();
        final AtomicReference<Throwable> error = new AtomicReference<Throwable>();
        Observable.create(new OnSubscribeFunc<String>() {

            @Override
            public Subscription onSubscribe(Observer<? super String> observer) {
                observer.onNext("1");
                observer.onNext("2");
                observer.onNext("three");
                observer.onNext("4");
                observer.onCompleted();
                return Subscriptions.empty();
            }

}