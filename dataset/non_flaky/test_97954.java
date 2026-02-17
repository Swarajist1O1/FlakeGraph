class DummyClass_97954 {
    @Test
    public void testConcatSimple() {
        Observable<String> o1 = Observable.from("one", "two");
        Observable<String> o2 = Observable.from("three", "four");

        List<String> values = Observable.concat(o1, o2).toList().toBlockingObservable().single();

        assertEquals("one", values.get(0));
        assertEquals("two", values.get(1));
        assertEquals("three", values.get(2));
        assertEquals("four", values.get(3));
    }

}