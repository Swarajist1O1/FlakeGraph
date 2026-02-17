class DummyClass_97967 {
    @Test
    public void testCountAFewItems() {
        Observable<String> observable = Observable.from("a", "b", "c", "d");
        observable.count().subscribe(w);
        // we should be called only once
        verify(w, times(1)).onNext(anyInt());
        verify(w).onNext(4);
        verify(w, never()).onError(any(Throwable.class));
        verify(w, times(1)).onCompleted();
    }

}