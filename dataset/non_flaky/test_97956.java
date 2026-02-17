class DummyClass_97956 {
    @Test
    public void testConcatWithIterableOfObservable() {
        Observable<String> o1 = Observable.from("one", "two");
        Observable<String> o2 = Observable.from("three", "four");
        Observable<String> o3 = Observable.from("five", "six");

        @SuppressWarnings("unchecked")
        Iterable<Observable<String>> is = Arrays.asList(o1, o2, o3);

        List<String> values = Observable.concat(Observable.from(is)).toList().toBlockingObservable().single();

        assertEquals("one", values.get(0));
        assertEquals("two", values.get(1));
        assertEquals("three", values.get(2));
        assertEquals("four", values.get(3));
    }

}