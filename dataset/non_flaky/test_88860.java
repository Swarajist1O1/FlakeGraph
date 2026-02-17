class DummyClass_88860 {
    @Test(expected = ConcurrentModificationException.class)
    public void testHasNextWhenIteratorHasLessElementsThanExpected() {
        List<Integer> list = Arrays.asList(1, 2, 3);

        Iterator<Integer> iter = new IteratorWithConcurrentModificationChecker<>(list.iterator(), 4, "Exception");

        assertTrue(iter.hasNext());
        iter.next();
        assertTrue(iter.hasNext());
        iter.next();
        assertTrue(iter.hasNext());
        iter.next();

        iter.hasNext(); // Should throw an exception.
    }

}