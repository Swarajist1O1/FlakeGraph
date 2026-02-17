class DummyClass_43032 {
    @Test(timeOut = 30_000)
    public void testJoinWithMultipleDynamicFiltersOnProbe()
    {
        // supplier names Supplier#000000001 and Supplier#000000002 match suppkey 1 and 2
        @Language("SQL") String selectQuery = "SELECT * FROM (" +
                "SELECT supplier.suppkey FROM " +
                "partitioned_lineitem JOIN tpch.tiny.supplier ON partitioned_lineitem.suppkey = supplier.suppkey AND supplier.name IN ('Supplier#000000001', 'Supplier#000000002')" +
                ") t JOIN supplier ON t.suppkey = supplier.suppkey AND supplier.suppkey IN (2, 3)";
        ResultWithQueryId<MaterializedResult> result = getDistributedQueryRunner().executeWithQueryId(
                getSession(),
                selectQuery);
        MaterializedResult expected = computeActual(withDynamicFilteringDisabled(), selectQuery);
        assertEqualsIgnoreOrder(result.getResult(), expected);

        OperatorStats probeStats = searchScanFilterAndProjectOperatorStats(result.getQueryId(), getQualifiedTableName(PARTITIONED_LINEITEM));
        // Probe-side is partially scanned
        assertEquals(probeStats.getInputPositions(), 558L);

        DynamicFiltersStats dynamicFiltersStats = getDynamicFilteringStats(result.getQueryId());
        assertEquals(dynamicFiltersStats.getTotalDynamicFilters(), 2L);
        assertEquals(dynamicFiltersStats.getLazyDynamicFilters(), 2L);
        assertEquals(dynamicFiltersStats.getReplicatedDynamicFilters(), 0L);
        assertEquals(dynamicFiltersStats.getDynamicFiltersCompleted(), 2);

        List<DynamicFilterDomainStats> domainStats = dynamicFiltersStats.getDynamicFilterDomainStats();
        assertThat(domainStats).map(DynamicFilterDomainStats::getSimplifiedDomain)
                .containsExactlyInAnyOrder(
                        getSimplifiedDomainString(2L, 3L, 2, BIGINT),
                        getSimplifiedDomainString(2L, 2L, 1, BIGINT));
    }

}