class DummyClass_97968 {
    @Test
    public void testCountZeroItems() {
        Observable<String> observable = Observable.empty();
        observable.count().subscribe(w);
        // we should be called only once
        verify(w, times(1)).onNext(anyInt());
        verify(w).onNext(0);
        verify(w, never()).onError(any(Throwable.class));
        verify(w, times(1)).onCompleted();
    }

}