class DummyClass_97980 {
    @Test
    public void testCustomObservableWithErrorInObservableSynchronous() {
        final AtomicInteger count = new AtomicInteger();
        final AtomicReference<Throwable> error = new AtomicReference<Throwable>();
        Observable.create(new OnSubscribeFunc<String>() {

            @Override
            public Subscription onSubscribe(Observer<? super String> observer) {
                observer.onNext("1");
                observer.onNext("2");
                throw new NumberFormatException();
            }

}