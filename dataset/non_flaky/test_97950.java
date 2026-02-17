class DummyClass_97950 {
    @Test
    public void testCovarianceOfZip() {
        Observable<HorrorMovie> horrors = Observable.from(new HorrorMovie());
        Observable<CoolRating> ratings = Observable.from(new CoolRating());

        Observable.<Movie, CoolRating, Result> zip(horrors, ratings, combine).toBlockingObservable().forEach(action);
        Observable.<Movie, CoolRating, Result> zip(horrors, ratings, combine).toBlockingObservable().forEach(action);
        Observable.<Media, Rating, ExtendedResult> zip(horrors, ratings, combine).toBlockingObservable().forEach(extendedAction);
        Observable.<Media, Rating, Result> zip(horrors, ratings, combine).toBlockingObservable().forEach(action);
        Observable.<Media, Rating, ExtendedResult> zip(horrors, ratings, combine).toBlockingObservable().forEach(action);

        Observable.<Movie, CoolRating, Result> zip(horrors, ratings, combine);
    }

}