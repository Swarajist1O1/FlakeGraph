class DummyClass_99731 {
    @Test
    public void testResultHandler() throws IOException
    {
        List<String> targetHosts = Lists.newArrayList("hosta", "hostb", "hostc");
        File tmpDir = Files.createTempDirectory("testresulthandler").toFile();
        File queryDir = Files.createTempDirectory("queries").toFile();
        List<File> resultPaths = new ArrayList<>();
        targetHosts.forEach(host -> { File f = new File(tmpDir, host); f.mkdir(); resultPaths.add(f);});

        ResultHandler.ComparableResultSet res = createResultSet(10, 10, false);
        ResultHandler.ComparableResultSet res2 = createResultSet(10, 10, false);
        ResultHandler.ComparableResultSet res3 = createResultSet(10, 10, false);
        List<ResultHandler.ComparableResultSet> toCompare = Lists.newArrayList(res, res2, res3);
        FQLQuery query = new FQLQuery.Single("abcabc", QueryOptions.DEFAULT.getProtocolVersion().asInt(), QueryOptions.DEFAULT, 1111, 2222, 3333, "select * from xyz", Collections.emptyList());
        try (ResultHandler rh = new ResultHandler(targetHosts, resultPaths, queryDir))
        {
            rh.handleResults(query, toCompare);
        }
        List<Pair<FQLQuery, ResultHandler.ComparableResultSet>> results1 = readResultFile(resultPaths.get(0), queryDir);
        List<Pair<FQLQuery, ResultHandler.ComparableResultSet>> results2 = readResultFile(resultPaths.get(1), queryDir);
        List<Pair<FQLQuery, ResultHandler.ComparableResultSet>> results3 = readResultFile(resultPaths.get(2), queryDir);
        compareResults(results1, results2);
        compareResults(results1, results3);
        compareResults(results3, Collections.singletonList(Pair.create(query, res)));
    }

}