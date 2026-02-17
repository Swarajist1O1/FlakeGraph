class DummyClass_97987 {
    @Test
    public void testOfType() {
        Observable<String> observable = Observable.from(1, "abc", false, 2L).ofType(String.class);

        @SuppressWarnings("unchecked")
        Observer<Object> aObserver = mock(Observer.class);
        observable.subscribe(aObserver);
        verify(aObserver, never()).onNext(1);
        verify(aObserver, times(1)).onNext("abc");
        verify(aObserver, never()).onNext(false);
        verify(aObserver, never()).onNext(2L);
        verify(aObserver, never()).onError(
                org.mockito.Matchers.any(Throwable.class));
        verify(aObserver, times(1)).onCompleted();
    }

}