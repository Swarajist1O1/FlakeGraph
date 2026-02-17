class DummyClass_43048 {
    @Test
    public void testIsNullPredicate()
    {
        assertQueryReturnsEmptyResult("SELECT * FROM orders WHERE orderkey IS NULL");
        assertQueryReturnsEmptyResult("SELECT * FROM orders WHERE orderkey = 10 OR orderkey IS NULL");

        // filtered column is selected
        assertQuery("SELECT custkey, orderkey FROM orders WHERE orderkey = 32 OR orderkey IS NULL", "VALUES (1301, 32)");

        // filtered column is not selected
        assertQuery("SELECT custkey FROM orders WHERE orderkey = 32 OR orderkey IS NULL", "VALUES (1301)");
    }

}