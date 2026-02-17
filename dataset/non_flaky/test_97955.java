class DummyClass_97955 {
    @Test
    public void testConcatWithObservableOfObservable() {
        Observable<String> o1 = Observable.from("one", "two");
        Observable<String> o2 = Observable.from("three", "four");
        Observable<String> o3 = Observable.from("five", "six");

        Observable<Observable<String>> os = Observable.from(o1, o2, o3);

        List<String> values = Observable.concat(os).toList().toBlockingObservable().single();

        assertEquals("one", values.get(0));
        assertEquals("two", values.get(1));
        assertEquals("three", values.get(2));
        assertEquals("four", values.get(3));
    }

}