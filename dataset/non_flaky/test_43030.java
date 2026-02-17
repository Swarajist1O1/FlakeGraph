class DummyClass_43030 {
    @Test(timeOut = 30_000)
    public void testJoinWithNonSelectiveBuildSide()
    {
        @Language("SQL") String selectQuery = "SELECT * FROM partitioned_lineitem JOIN supplier ON partitioned_lineitem.suppkey = supplier.suppkey";
        ResultWithQueryId<MaterializedResult> result = getDistributedQueryRunner().executeWithQueryId(
                getSession(),
                selectQuery);
        MaterializedResult expected = computeActual(withDynamicFilteringDisabled(), selectQuery);
        assertEqualsIgnoreOrder(result.getResult(), expected);

        OperatorStats probeStats = searchScanFilterAndProjectOperatorStats(result.getQueryId(), getQualifiedTableName(PARTITIONED_LINEITEM));
        // Probe-side is fully scanned
        assertEquals(probeStats.getInputPositions(), LINEITEM_COUNT);

        DynamicFiltersStats dynamicFiltersStats = getDynamicFilteringStats(result.getQueryId());
        assertEquals(dynamicFiltersStats.getTotalDynamicFilters(), 1L);
        assertEquals(dynamicFiltersStats.getLazyDynamicFilters(), 1L);
        assertEquals(dynamicFiltersStats.getReplicatedDynamicFilters(), 0L);
        assertEquals(dynamicFiltersStats.getDynamicFiltersCompleted(), 1L);

        DynamicFilterDomainStats domainStats = getOnlyElement(dynamicFiltersStats.getDynamicFilterDomainStats());
        assertThat(domainStats.getSimplifiedDomain())
                .isEqualTo(getSimplifiedDomainString(1L, 100L, 100, BIGINT));
    }

}