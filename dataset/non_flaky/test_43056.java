class DummyClass_43056 {
    @Test(timeOut = 300_000, dataProvider = "joinDistributionTypes")
    public void testJoinWithEmptySides(JoinDistributionType joinDistributionType)
    {
        Session session = noJoinReordering(joinDistributionType);
        // empty build side
        assertQuery(session, "SELECT count(*) FROM nation JOIN region ON nation.regionkey = region.regionkey AND region.name = ''", "VALUES 0");
        assertQuery(session, "SELECT count(*) FROM nation JOIN region ON nation.regionkey = region.regionkey AND region.regionkey < 0", "VALUES 0");
        // empty probe side
        assertQuery(session, "SELECT count(*) FROM region JOIN nation ON nation.regionkey = region.regionkey AND region.name = ''", "VALUES 0");
        assertQuery(session, "SELECT count(*) FROM nation JOIN region ON nation.regionkey = region.regionkey AND region.regionkey < 0", "VALUES 0");
    }

}