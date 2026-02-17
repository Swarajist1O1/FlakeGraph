class DummyClass_97969 {
    @Test
    public void testCountError() {
        Observable<String> o = Observable.create(new OnSubscribeFunc<String>() {
            @Override
            public Subscription onSubscribe(Observer<? super String> obsv) {
                obsv.onError(new RuntimeException());
                return Subscriptions.empty();
            }

}