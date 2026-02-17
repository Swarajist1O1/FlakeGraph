class DummyClass_99726 {
    @Test
    public void testCompareColumnDefinitions()
    {
        ResultHandler.ComparableResultSet res = createResultSet(10, 10, false);
        ResultComparator rc = new ResultComparator();

        List<ResultHandler.ComparableColumnDefinitions> colDefs = new ArrayList<>(100);
        List<String> targetHosts = new ArrayList<>(100);
        for (int i = 0; i < 100; i++)
        {
            targetHosts.add("host"+i);
            colDefs.add(res.getColumnDefinitions());
        }
        assertTrue(rc.compareColumnDefinitions(targetHosts, null, colDefs));
        colDefs.set(50, createResultSet(9, 9, false).getColumnDefinitions());
        assertFalse(rc.compareColumnDefinitions(targetHosts, null, colDefs));
    }

}