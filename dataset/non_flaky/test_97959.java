class DummyClass_97959 {
    @Test
    public void testConcatCovariance3() {
        Observable<Movie> o1 = Observable.from(new HorrorMovie(), new Movie());
        Observable<Media> o2 = Observable.from(new Media(), new HorrorMovie());

        List<Media> values = Observable.concat(o1, o2).toList().toBlockingObservable().single();
        
        assertTrue(values.get(0) instanceof HorrorMovie);
        assertTrue(values.get(1) instanceof Movie);
        assertTrue(values.get(2) instanceof Media);
        assertTrue(values.get(3) instanceof HorrorMovie);
    }

}