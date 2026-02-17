class DummyClass_97957 {
    @Test
    public void testConcatCovariance() {
        Observable<Media> o1 = Observable.<Media> from(new HorrorMovie(), new Movie());
        Observable<Media> o2 = Observable.from(new Media(), new HorrorMovie());

        Observable<Observable<Media>> os = Observable.from(o1, o2);

        List<Media> values = Observable.concat(os).toList().toBlockingObservable().single();
    }

}