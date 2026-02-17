class DummyClass_97964 {
    @Test
    public void fromArityArgs3() {
        Observable<String> items = Observable.from("one", "two", "three");

        assertEquals(new Integer(3), items.count().toBlockingObservable().single());
        assertEquals("two", items.skip(1).take(1).toBlockingObservable().single());
        assertEquals("three", items.takeLast(1).toBlockingObservable().single());
    }

}