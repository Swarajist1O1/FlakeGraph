class DummyClass_99734 {
    @Test
    public void testResultHandlerFailedQuery() throws IOException
    {
        List<String> targetHosts = Lists.newArrayList("hosta", "hostb", "hostc", "hostd");
        File tmpDir = Files.createTempDirectory("testresulthandler").toFile();
        File queryDir = Files.createTempDirectory("queries").toFile();
        List<File> resultPaths = new ArrayList<>();
        targetHosts.forEach(host -> { File f = new File(tmpDir, host); f.mkdir(); resultPaths.add(f);});

        List<Pair<FQLQuery, List<ResultHandler.ComparableResultSet>>> resultSets = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 10; i++)
        {
            List<ResultHandler.ComparableResultSet> results = new ArrayList<>();
            List<ByteBuffer> values = Collections.singletonList(ByteBufferUtil.bytes(i * 50));
            for (int jj = 0; jj < targetHosts.size(); jj++)
            {
                results.add(createResultSet(5, 1 + random.nextInt(10), true));
            }
            results.set(0, StoredResultSet.failed("testing abc"));
            results.set(3, StoredResultSet.failed("testing abc"));
            FQLQuery q = new FQLQuery.Single("abc"+i,
                                             3,
                                             QueryOptions.forInternalCalls(values),
                                             i * 1000,
                                             i * 12345,
                                             i * 54321,
                                             "select * from xyz where id = "+i,
                                             values);
            resultSets.add(Pair.create(q, results));
        }
        try (ResultHandler rh = new ResultHandler(targetHosts, resultPaths, queryDir))
        {
            for (int i = 0; i < resultSets.size(); i++)
                rh.handleResults(resultSets.get(i).left, resultSets.get(i).right);
        }
        for (int i = 0; i < targetHosts.size(); i++)
            compareWithFile(resultPaths, queryDir, resultSets, i);
    }

}