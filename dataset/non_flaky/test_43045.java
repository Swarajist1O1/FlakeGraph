class DummyClass_43045 {
    @Test
    public void testAggregation()
    {
        assertQuery("SELECT sum(orderkey) FROM orders");
        assertQuery("SELECT sum(totalprice) FROM orders");
        assertQuery("SELECT max(comment) FROM nation");

        assertQuery("SELECT count(*) FROM orders");
        assertQuery("SELECT count(*) FROM orders WHERE orderkey > 10");
        assertQuery("SELECT count(*) FROM (SELECT * FROM orders LIMIT 10)");
        assertQuery("SELECT count(*) FROM (SELECT * FROM orders WHERE orderkey > 10 LIMIT 10)");

        assertQuery("SELECT DISTINCT regionkey FROM nation");
        assertQuery("SELECT regionkey FROM nation GROUP BY regionkey");

        // TODO support aggregation pushdown with GROUPING SETS
        assertQuery(
                "SELECT regionkey, nationkey FROM nation GROUP BY GROUPING SETS ((regionkey), (nationkey))",
                "SELECT NULL, nationkey FROM nation " +
                        "UNION ALL SELECT DISTINCT regionkey, NULL FROM nation");
        assertQuery(
                "SELECT regionkey, nationkey, count(*) FROM nation GROUP BY GROUPING SETS ((), (regionkey), (nationkey), (regionkey, nationkey))",
                "SELECT NULL, NULL, count(*) FROM nation " +
                        "UNION ALL SELECT NULL, nationkey, 1 FROM nation " +
                        "UNION ALL SELECT regionkey, NULL, count(*) FROM nation GROUP BY regionkey " +
                        "UNION ALL SELECT regionkey, nationkey, 1 FROM nation");

        assertQuery("SELECT count(regionkey) FROM nation");
        assertQuery("SELECT count(DISTINCT regionkey) FROM nation");
        assertQuery("SELECT regionkey, count(*) FROM nation GROUP BY regionkey");

        assertQuery("SELECT min(regionkey), max(regionkey) FROM nation");
        assertQuery("SELECT min(DISTINCT regionkey), max(DISTINCT regionkey) FROM nation");
        assertQuery("SELECT regionkey, min(regionkey), min(name), max(regionkey), max(name) FROM nation GROUP BY regionkey");

        assertQuery("SELECT sum(regionkey) FROM nation");
        assertQuery("SELECT sum(DISTINCT regionkey) FROM nation");
        assertQuery("SELECT regionkey, sum(regionkey) FROM nation GROUP BY regionkey");

        assertQuery(
                "SELECT avg(nationkey) FROM nation",
                "SELECT avg(CAST(nationkey AS double)) FROM nation");
        assertQuery(
                "SELECT avg(DISTINCT nationkey) FROM nation",
                "SELECT avg(DISTINCT CAST(nationkey AS double)) FROM nation");
        assertQuery(
                "SELECT regionkey, avg(nationkey) FROM nation GROUP BY regionkey",
                "SELECT regionkey, avg(CAST(nationkey AS double)) FROM nation GROUP BY regionkey");
    }

}