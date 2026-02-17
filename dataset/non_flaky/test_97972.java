class DummyClass_97972 {
    @Test
    public void testFirstOfNone() {
        Observable<Integer> observable = Observable.empty();
        observable.first().subscribe(w);
        verify(w, never()).onNext(anyInt());
        verify(w, times(1)).onCompleted();
        verify(w, never()).onError(any(Throwable.class));
    }

}