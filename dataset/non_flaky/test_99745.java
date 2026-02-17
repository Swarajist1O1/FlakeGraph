class DummyClass_99745 {
    @Test
    public void compareEqual() throws IOException
    {
        List<String> targetHosts = Lists.newArrayList("hosta", "hostb");
        File tmpDir = Files.createTempDirectory("testresulthandler").toFile();
        File queryDir = Files.createTempDirectory("queries").toFile();
        List<File> resultPaths = generateResultSets(targetHosts, tmpDir, queryDir, false,false);

        ResultComparator comparator = new ResultComparator();
        List<ChronicleQueue> readQueues = null;
        try
        {
            readQueues = resultPaths.stream().map(s -> ChronicleQueueBuilder.single(s).readOnly(true).build()).collect(Collectors.toList());
            List<Iterator<ResultHandler.ComparableResultSet>> its = readQueues.stream().map(q -> new Compare.StoredResultSetIterator(q.createTailer())).collect(Collectors.toList());
            List<ResultHandler.ComparableResultSet> resultSets = Compare.resultSets(its);
            while(resultSets.stream().allMatch(Objects::nonNull))
            {
                assertTrue(comparator.compareColumnDefinitions(targetHosts, query(), resultSets.stream().map(ResultHandler.ComparableResultSet::getColumnDefinitions).collect(Collectors.toList())));
                List<Iterator<ResultHandler.ComparableRow>> rows = resultSets.stream().map(Iterable::iterator).collect(Collectors.toList());

                List<ResultHandler.ComparableRow> toCompare = ResultHandler.rows(rows);

                while (toCompare.stream().allMatch(Objects::nonNull))
                {
                    assertTrue(comparator.compareRows(targetHosts, query(), ResultHandler.rows(rows)));
                    toCompare = ResultHandler.rows(rows);
                }
                resultSets = Compare.resultSets(its);
            }
        }
        finally
        {
            if (readQueues != null)
                readQueues.forEach(Closeable::close);
        }
    }

}