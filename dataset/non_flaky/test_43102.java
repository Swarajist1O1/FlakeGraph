class DummyClass_43102 {
    @Test
    public void testExplainAnalyzeVerbose()
    {
        assertExplainAnalyze("EXPLAIN ANALYZE VERBOSE SELECT * FROM orders");
        assertExplainAnalyze("EXPLAIN ANALYZE VERBOSE SELECT rank() OVER (PARTITION BY orderkey ORDER BY clerk DESC) FROM orders");
        assertExplainAnalyze("EXPLAIN ANALYZE VERBOSE SELECT rank() OVER (PARTITION BY orderkey ORDER BY clerk DESC) FROM orders WHERE orderkey < 0");
    }

}