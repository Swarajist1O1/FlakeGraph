class DummyClass_97963 {
    @Test
    public void fromIterable() {
        ArrayList<String> items = new ArrayList<String>();
        items.add("one");
        items.add("two");
        items.add("three");

        assertEquals(new Integer(3), Observable.from(items).count().toBlockingObservable().single());
        assertEquals("two", Observable.from(items).skip(1).take(1).toBlockingObservable().single());
        assertEquals("three", Observable.from(items).takeLast(1).toBlockingObservable().single());
    }

}