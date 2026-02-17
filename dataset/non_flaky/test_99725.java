class DummyClass_99725 {
    @Test
    public void testStoringResults() throws Throwable
    {
        File tmpDir = Files.createTempDirectory("results").toFile();
        File queryDir = Files.createTempDirectory("queries").toFile();

        ResultHandler.ComparableResultSet res = createResultSet(10, 10, true);
        ResultStore rs = new ResultStore(Collections.singletonList(tmpDir), queryDir);
        FQLQuery query = new FQLQuery.Single("abc", QueryOptions.DEFAULT.getProtocolVersion().asInt(), QueryOptions.DEFAULT, 12345, 11111, 22, "select * from abc", Collections.emptyList());
        try
        {
            rs.storeColumnDefinitions(query, Collections.singletonList(res.getColumnDefinitions()));
            Iterator<ResultHandler.ComparableRow> it = res.iterator();
            while (it.hasNext())
            {
                List<ResultHandler.ComparableRow> row = Collections.singletonList(it.next());
                rs.storeRows(row);
            }
            // this marks the end of the result set:
            rs.storeRows(Collections.singletonList(null));
        }
        finally
        {
            rs.close();
        }

        compareResults(Collections.singletonList(Pair.create(query, res)),
                       readResultFile(tmpDir, queryDir));

    }

}