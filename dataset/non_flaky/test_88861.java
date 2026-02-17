class DummyClass_88861 {
    @Test(expected = ConcurrentModificationException.class)
    public void testHasNextWhenIteratorHasMoreElementsThanExpected() {
        List<Integer> list = Arrays.asList(1, 2, 3);

        Iterator<Integer> iter = new IteratorWithConcurrentModificationChecker<>(list.iterator(), 2, "Exception");

        assertTrue(iter.hasNext());
        iter.next();
        assertTrue(iter.hasNext());
        iter.next();

        iter.hasNext(); // Should throw an exception.
    }

}