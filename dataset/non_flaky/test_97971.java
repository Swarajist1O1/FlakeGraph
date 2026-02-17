class DummyClass_97971 {
    @Test
    public void testFirstOfSome() {
        Observable<Integer> observable = Observable.from(1, 2, 3);
        observable.first().subscribe(w);
        verify(w, times(1)).onNext(anyInt());
        verify(w).onNext(1);
        verify(w, times(1)).onCompleted();
        verify(w, never()).onError(any(Throwable.class));
    }

}