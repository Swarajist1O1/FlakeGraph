class DummyClass_98002 {
    @Test
    public void testCovarianceOfCombineLatest() {
        Observable<HorrorMovie> horrors = Observable.from(new HorrorMovie());
        Observable<CoolRating> ratings = Observable.from(new CoolRating());

        Observable.<Movie, CoolRating, Result> combineLatest(horrors, ratings, combine).toBlockingObservable().forEach(action);
        Observable.<Movie, CoolRating, Result> combineLatest(horrors, ratings, combine).toBlockingObservable().forEach(action);
        Observable.<Media, Rating, ExtendedResult> combineLatest(horrors, ratings, combine).toBlockingObservable().forEach(extendedAction);
        Observable.<Media, Rating, Result> combineLatest(horrors, ratings, combine).toBlockingObservable().forEach(action);
        Observable.<Media, Rating, ExtendedResult> combineLatest(horrors, ratings, combine).toBlockingObservable().forEach(action);

        Observable.<Movie, CoolRating, Result> combineLatest(horrors, ratings, combine);
    }

}