class DummyClass_43057 {
    @Test
    public void testJoin()
    {
        Session session = Session.builder(getSession())
                .setSystemProperty(IGNORE_STATS_CALCULATOR_FAILURES, "false")
                .build();

        // 2 inner joins, eligible for join reodering
        assertQuery(
                session,
                "SELECT c.name, n.name, r.name " +
                        "FROM nation n " +
                        "JOIN customer c ON c.nationkey = n.nationkey " +
                        "JOIN region r ON n.regionkey = r.regionkey");

        // 2 inner joins, eligible for join reodering, where one table has a filter
        assertQuery(
                session,
                "SELECT c.name, n.name, r.name " +
                        "FROM nation n " +
                        "JOIN customer c ON c.nationkey = n.nationkey " +
                        "JOIN region r ON n.regionkey = r.regionkey " +
                        "WHERE n.name = 'ARGENTINA'");

        // 2 inner joins, eligible for join reodering, on top of aggregation
        assertQuery(
                session,
                "SELECT c.name, n.name, n.count, r.name " +
                        "FROM (SELECT name, regionkey, nationkey, count(*) count FROM nation GROUP BY name, regionkey, nationkey) n " +
                        "JOIN customer c ON c.nationkey = n.nationkey " +
                        "JOIN region r ON n.regionkey = r.regionkey");
    }

}