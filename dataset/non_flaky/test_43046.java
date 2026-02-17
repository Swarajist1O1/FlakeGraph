class DummyClass_43046 {
    @Test
    public void testExactPredicate()
    {
        assertQueryReturnsEmptyResult("SELECT * FROM orders WHERE orderkey = 10");

        // filtered column is selected
        assertQuery("SELECT custkey, orderkey FROM orders WHERE orderkey = 32", "VALUES (1301, 32)");

        // filtered column is not selected
        assertQuery("SELECT custkey FROM orders WHERE orderkey = 32", "VALUES (1301)");
    }

}