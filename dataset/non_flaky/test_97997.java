class DummyClass_97997 {
    @Test
    public void testMergeCovariance4() {

        Observable<Movie> o1 = Observable.create(new OnSubscribeFunc<Movie>() {

            @Override
            public Subscription onSubscribe(Observer<? super Movie> o) {
                o.onNext(new HorrorMovie());
                o.onNext(new Movie());
                //                o.onNext(new Media()); // correctly doesn't compile
                o.onCompleted();
                return Subscriptions.empty();
            }

}