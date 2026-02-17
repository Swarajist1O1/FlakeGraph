class DummyClass_97993 {
    @Test
    public void testCovarianceOfMerge() {
        Observable<HorrorMovie> horrors = Observable.from(new HorrorMovie());
        Observable<Observable<HorrorMovie>> metaHorrors = Observable.just(horrors);
        Observable.<Media> merge(metaHorrors);
    }

}