class DummyClass_97977 {
    @Test
    public void testMaterializeDematerializeChaining() {
        Observable<Integer> obs = Observable.just(1);
        Observable<Integer> chained = obs.materialize().dematerialize();

        @SuppressWarnings("unchecked")
        Observer<Integer> observer = mock(Observer.class);
        chained.subscribe(observer);

        verify(observer, times(1)).onNext(1);
        verify(observer, times(1)).onCompleted();
        verify(observer, times(0)).onError(any(Throwable.class));
    }

}