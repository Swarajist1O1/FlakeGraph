class DummyClass_99728 {
    @Test
    public void testCompareRowsDifferentCount()
    {
        ResultComparator rc = new ResultComparator();
        ResultHandler.ComparableResultSet res = createResultSet(10, 10, false);
        ResultHandler.ComparableResultSet res2 = createResultSet(10, 10, false);
        List<ResultHandler.ComparableResultSet> toCompare = Lists.newArrayList(res, res2, createResultSet(10, 11, false));
        List<Iterator<ResultHandler.ComparableRow>> iters = toCompare.stream().map(Iterable::iterator).collect(Collectors.toList());
        boolean foundMismatch = false;
        while (true)
        {
            List<ResultHandler.ComparableRow> rows = ResultHandler.rows(iters);
            if (rows.stream().allMatch(Objects::isNull))
                break;
            if (!rc.compareRows(Lists.newArrayList("eq1", "eq2", "diff"), null, rows))
            {
                foundMismatch = true;
            }
        }
        assertTrue(foundMismatch);
    }

}