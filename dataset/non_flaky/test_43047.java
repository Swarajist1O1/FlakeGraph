class DummyClass_43047 {
    @Test
    public void testInListPredicate()
    {
        assertQueryReturnsEmptyResult("SELECT * FROM orders WHERE orderkey IN (10, 11, 20, 21)");

        // filtered column is selected
        assertQuery("SELECT custkey, orderkey FROM orders WHERE orderkey IN (7, 10, 32, 33)", "VALUES (392, 7), (1301, 32), (670, 33)");

        // filtered column is not selected
        assertQuery("SELECT custkey FROM orders WHERE orderkey IN (7, 10, 32, 33)", "VALUES (392), (1301), (670)");
    }

}