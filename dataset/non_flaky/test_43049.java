class DummyClass_43049 {
    @Test
    public void testLikePredicate()
    {
        // filtered column is not selected
        assertQuery("SELECT orderkey FROM orders WHERE orderpriority LIKE '5-L%'");

        // filtered column is selected
        assertQuery("SELECT orderkey, orderpriority FROM orders WHERE orderpriority LIKE '5-L%'");

        // filtered column is not selected
        assertQuery("SELECT orderkey FROM orders WHERE orderpriority LIKE '5-L__'");

        // filtered column is selected
        assertQuery("SELECT orderkey, orderpriority FROM orders WHERE orderpriority LIKE '5-L__'");
    }

}