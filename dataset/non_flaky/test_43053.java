class DummyClass_43053 {
    @Test
    public void testSortItemsReflectedInExplain()
    {
        // Even if the sort items are pushed down into the table scan, it should still be reflected in EXPLAIN (via ConnectorTableHandle.toString)
        @Language("RegExp") String expectedPattern = hasBehavior(SUPPORTS_TOPN_PUSHDOWN)
                ? "sortOrder=\\[(?i:nationkey):.* DESC NULLS LAST] limit=5"
                : "\\[5 by \\((?i:nationkey) DESC NULLS LAST\\)]";

        assertExplain(
                "EXPLAIN SELECT name FROM nation ORDER BY nationkey DESC NULLS LAST LIMIT 5",
                expectedPattern);
    }

}