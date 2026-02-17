class DummyClass_43033 {
    @Test(timeOut = 30_000)
    public void testJoinWithImplicitCoercion()
    {
        // setup partitioned fact table with integer suppkey
        createLineitemTable("partitioned_lineitem_int", ImmutableList.of("orderkey", "CAST(suppkey as int) suppkey_int"), ImmutableList.of("suppkey_int"));
        assertQuery(
                "SELECT count(*) FROM partitioned_lineitem_int",
                "VALUES " + LINEITEM_COUNT);

        @Language("SQL") String selectQuery = "SELECT * FROM partitioned_lineitem_int l JOIN supplier s ON l.suppkey_int = s.suppkey AND s.name = 'Supplier#000000001'";
        ResultWithQueryId<MaterializedResult> result = getDistributedQueryRunner().executeWithQueryId(
                getSession(),
                selectQuery);
        MaterializedResult expected = computeActual(withDynamicFilteringDisabled(), selectQuery);
        assertEqualsIgnoreOrder(result.getResult(), expected);

        OperatorStats probeStats = searchScanFilterAndProjectOperatorStats(result.getQueryId(), getQualifiedTableName("partitioned_lineitem_int"));
        // Probe-side is partially scanned
        assertEquals(probeStats.getInputPositions(), 615L);

        DynamicFiltersStats dynamicFiltersStats = getDynamicFilteringStats(result.getQueryId());
        assertEquals(dynamicFiltersStats.getTotalDynamicFilters(), 1L);
        assertEquals(dynamicFiltersStats.getLazyDynamicFilters(), 1L);
        assertEquals(dynamicFiltersStats.getReplicatedDynamicFilters(), 0L);
        assertEquals(dynamicFiltersStats.getDynamicFiltersCompleted(), 1L);
        DynamicFilterDomainStats domainStats = getOnlyElement(dynamicFiltersStats.getDynamicFilterDomainStats());
        assertEquals(domainStats.getSimplifiedDomain(), singleValue(BIGINT, 1L).toString(getSession().toConnectorSession()));
    }

}