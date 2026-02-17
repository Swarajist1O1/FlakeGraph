class DummyClass_43038 {
    @Test(timeOut = 30_000)
    public void testRightJoinWithEmptyBuildSide()
    {
        @Language("SQL") String selectQuery = "SELECT * FROM partitioned_lineitem l RIGHT JOIN supplier s ON l.suppkey = s.suppkey WHERE name = 'abc'";
        ResultWithQueryId<MaterializedResult> result = getDistributedQueryRunner().executeWithQueryId(
                getSession(),
                selectQuery);
        MaterializedResult expected = computeActual(withDynamicFilteringDisabled(), selectQuery);
        assertEqualsIgnoreOrder(result.getResult(), expected);

        OperatorStats probeStats = searchScanFilterAndProjectOperatorStats(result.getQueryId(), getQualifiedTableName(PARTITIONED_LINEITEM));
        assertEquals(probeStats.getInputPositions(), 0L);

        DynamicFiltersStats dynamicFiltersStats = getDynamicFilteringStats(result.getQueryId());
        assertEquals(dynamicFiltersStats.getTotalDynamicFilters(), 1L);
        assertEquals(dynamicFiltersStats.getLazyDynamicFilters(), 1L);
        assertEquals(dynamicFiltersStats.getReplicatedDynamicFilters(), 0L);
        assertEquals(dynamicFiltersStats.getDynamicFiltersCompleted(), 1L);

        DynamicFilterDomainStats domainStats = getOnlyElement(dynamicFiltersStats.getDynamicFilterDomainStats());
        assertEquals(domainStats.getSimplifiedDomain(), none(BIGINT).toString(getSession().toConnectorSession()));
    }

}