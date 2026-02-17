class DummyClass_97965 {
    @Test
    public void fromArityArgs1() {
        Observable<String> items = Observable.from("one");

        assertEquals(new Integer(1), items.count().toBlockingObservable().single());
        assertEquals("one", items.takeLast(1).toBlockingObservable().single());
    }

}