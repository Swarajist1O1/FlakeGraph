class DummyClass_98006 {
    @Test
    public void startWithIterable() {
        List<String> li = new ArrayList<String>();
        li.add("alpha");
        li.add("beta");
        List<String> values = Observable.from("one", "two").startWith(li).toList().toBlockingObservable().single();

        assertEquals("alpha", values.get(0));
        assertEquals("beta", values.get(1));
        assertEquals("one", values.get(2));
        assertEquals("two", values.get(3));
    }

}