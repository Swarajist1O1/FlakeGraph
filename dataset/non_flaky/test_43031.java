class DummyClass_43031 {
    @Test(timeOut = 30_000)
    public void testJoinLargeBuildSideRangeDynamicFiltering()
    {
        @Language("SQL") String selectQuery = "SELECT * FROM partitioned_lineitem JOIN orders ON partitioned_lineitem.orderkey = orders.orderkey";
        ResultWithQueryId<MaterializedResult> result = getDistributedQueryRunner().executeWithQueryId(
                getSession(),
                selectQuery);
        MaterializedResult expected = computeActual(withDynamicFilteringDisabled(), selectQuery);
        assertEqualsIgnoreOrder(result.getResult(), expected);

        OperatorStats probeStats = searchScanFilterAndProjectOperatorStats(result.getQueryId(), getQualifiedTableName(PARTITIONED_LINEITEM));
        // Probe-side is fully scanned because the build-side is too large for dynamic filtering
        assertEquals(probeStats.getInputPositions(), LINEITEM_COUNT);

        DynamicFiltersStats dynamicFiltersStats = getDynamicFilteringStats(result.getQueryId());
        assertEquals(dynamicFiltersStats.getTotalDynamicFilters(), 1L);
        assertEquals(dynamicFiltersStats.getLazyDynamicFilters(), 1L);
        assertEquals(dynamicFiltersStats.getReplicatedDynamicFilters(), 0L);
        assertEquals(dynamicFiltersStats.getDynamicFiltersCompleted(), 1L);

        DynamicFilterDomainStats domainStats = getOnlyElement(dynamicFiltersStats.getDynamicFilterDomainStats());
        assertEquals(
                domainStats.getSimplifiedDomain(),
                Domain.create(ValueSet.ofRanges(range(BIGINT, 1L, true, 60000L, true)), false)
                        .toString(getSession().toConnectorSession()));
    }

}