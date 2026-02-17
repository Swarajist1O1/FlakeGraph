class DummyClass_43051 {
    @Test
    public void testRangePredicate()
    {
        // List columns explicitly. Some connectors do not maintain column ordering.
        assertQuery("" +
                "SELECT orderkey, custkey, orderstatus, totalprice, orderdate, orderpriority, clerk, shippriority, comment " +
                "FROM orders " +
                "WHERE orderkey BETWEEN 10 AND 50");
    }

}