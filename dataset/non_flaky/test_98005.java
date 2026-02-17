class DummyClass_98005 {
    @Test
    public void startWith1() {
        List<String> values = Observable.from("one", "two").startWith("zero").toList().toBlockingObservable().single();

        assertEquals("zero", values.get(0));
        assertEquals("two", values.get(2));
    }

}