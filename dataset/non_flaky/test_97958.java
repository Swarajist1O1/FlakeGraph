class DummyClass_97958 {
    @Test
    public void testConcatCovariance2() {
        Observable<Media> o1 = Observable.from(new HorrorMovie(), new Movie(), new Media());
        Observable<Media> o2 = Observable.from(new Media(), new HorrorMovie());

        Observable<Observable<Media>> os = Observable.from(o1, o2);

        List<Media> values = Observable.concat(os).toList().toBlockingObservable().single();
    }

}