class DummyClass_88858 {
    @Test(expected = ConcurrentModificationException.class)
    public void testNextWhenIteratorHasLessElementsThanExpected() {
        List<Integer> list = Arrays.asList(1, 2, 3);

        Iterator<Integer> iter = new IteratorWithConcurrentModificationChecker<>(list.iterator(), 4, "Exception");

        assertEquals(Integer.valueOf(1), iter.next());
        assertEquals(Integer.valueOf(2), iter.next());
        assertEquals(Integer.valueOf(3), iter.next());

        iter.next(); // Should throw an exception.
    }

}