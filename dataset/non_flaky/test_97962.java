class DummyClass_97962 {
    @Test
    public void fromArray() {
        String[] items = new String[] { "one", "two", "three" };
        assertEquals(new Integer(3), Observable.from(items).count().toBlockingObservable().single());
        assertEquals("two", Observable.from(items).skip(1).take(1).toBlockingObservable().single());
        assertEquals("three", Observable.from(items).takeLast(1).toBlockingObservable().single());
    }

}