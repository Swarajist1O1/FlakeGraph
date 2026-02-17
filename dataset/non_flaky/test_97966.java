class DummyClass_97966 {
    @Test
    public void testCreate() {

        Observable<String> observable = Observable.create(new OnSubscribeFunc<String>() {

            @Override
            public Subscription onSubscribe(Observer<? super String> Observer) {
                Observer.onNext("one");
                Observer.onNext("two");
                Observer.onNext("three");
                Observer.onCompleted();
                return Subscriptions.empty();
            }

}