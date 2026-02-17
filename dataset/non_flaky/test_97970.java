class DummyClass_97970 {
    @Test
    public void testFirstWithPredicateOfNoneMatchingThePredicate() {
        Observable<Integer> observable = Observable.from(1, 3, 5, 7, 9, 7, 5, 3, 1);
        observable.first(IS_EVEN).subscribe(w);
        verify(w, never()).onNext(anyInt());
        verify(w, times(1)).onCompleted();
        verify(w, never()).onError(any(Throwable.class));
    }

}