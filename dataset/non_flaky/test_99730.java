class DummyClass_99730 {
    @Test
    public void testCompareRowsDifferentColumnCount()
    {
        ResultComparator rc = new ResultComparator();
        ResultHandler.ComparableResultSet res = createResultSet(10, 10, false);
        ResultHandler.ComparableResultSet res2 = createResultSet(10, 10, false);
        List<ResultHandler.ComparableResultSet> toCompare = Lists.newArrayList(res, res2, createResultSet(11, 10, false));
        List<Iterator<ResultHandler.ComparableRow>> iters = toCompare.stream().map(Iterable::iterator).collect(Collectors.toList());
        while (true)
        {
            List<ResultHandler.ComparableRow> rows = ResultHandler.rows(iters);
            if (rows.stream().allMatch(Objects::isNull))
                break;
            assertFalse(rows.toString(), rc.compareRows(Lists.newArrayList("eq1", "eq2", "diff"), null, rows));
        }
    }

}