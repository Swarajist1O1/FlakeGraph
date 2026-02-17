class DummyClass_43052 {
    @Test
    public void testPredicateReflectedInExplain()
    {
        // Even if the predicate is pushed down into the table scan, it should still be reflected in EXPLAIN (via ConnectorTableHandle.toString)
        assertExplain(
                "EXPLAIN SELECT name FROM nation WHERE nationkey = 42",
                "(predicate|filterPredicate|constraint).{0,10}(nationkey|NATIONKEY)");
    }

}