class DummyClass_98249 {
    @Test
    public void testInsert() throws RepositoryException {
        ComparableArray ca = new ComparableArray("a", 1);
        assertEquals("a", ca.toString());
        assertEquals(1, ca.getOffset());

        // insert before
        ca.insert("b", 0);
        assertEquals("[b, a]", ca.toString());
        assertEquals(0, ca.getOffset());

        // insert after
        ca.insert("c", 3);
        assertEquals("[b, a, null, c]", ca.toString());
        assertEquals(0, ca.getOffset());

        // insert inside
        ca.insert("d", 2);
        assertEquals("[b, a, d, c]", ca.toString());
        assertEquals(0, ca.getOffset());
    }

}