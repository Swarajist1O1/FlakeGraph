class DummyClass_97975 {
    @Test
    public void testSequenceEqual() {
        Observable<Integer> first = Observable.from(1, 2, 3);
        Observable<Integer> second = Observable.from(1, 2, 4);
        @SuppressWarnings("unchecked")
        Observer<Boolean> result = mock(Observer.class);
        Observable.sequenceEqual(first, second).subscribe(result);
        verify(result, times(2)).onNext(true);
        verify(result, times(1)).onNext(false);
    }

}