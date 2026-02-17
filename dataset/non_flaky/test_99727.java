class DummyClass_99727 {
    @Test
    public void testCompareEqualRows()
    {
        ResultComparator rc = new ResultComparator();

        ResultHandler.ComparableResultSet res = createResultSet(10, 10, false);
        ResultHandler.ComparableResultSet res2 = createResultSet(10, 10, false);
        List<ResultHandler.ComparableResultSet> toCompare = Lists.newArrayList(res, res2);
        List<Iterator<ResultHandler.ComparableRow>> iters = toCompare.stream().map(Iterable::iterator).collect(Collectors.toList());

        while (true)
        {
            List<ResultHandler.ComparableRow> rows = ResultHandler.rows(iters);
            assertTrue(rc.compareRows(Lists.newArrayList("eq1", "eq2"), null, rows));
            if (rows.stream().allMatch(Objects::isNull))
                break;
        }
    }

}