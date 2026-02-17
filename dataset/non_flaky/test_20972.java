class DummyClass_20972 {
    @Test
    public void TestExtentOfStorage() {
        DataStoreCache mmStore = new DataStoreCache(configuration);

        HashMap<String, String> tags = new HashMap<>();
        tags.put("host", "localhost");

        long start = System.currentTimeMillis();
        long timestamp = start;

        for (int x = 1; x <= 100; x++) {

            Metric m = createMetric("test.metric", tags, 2.0, timestamp);
            mmStore.store(m);
            mmStore.flushCaches(-1);
            timestamp = timestamp + 60000;

            QueryRequest query = new QueryRequest();
            query.setStart(start);
            query.setEnd(start + 86400000);
            query.setMsResolution(true);
            QueryRequest.SubQuery subQuery = new QueryRequest.SubQuery();
            // subQuery.setDownsample(Optional.of("5m-avg"));
            subQuery.setMetric("test.metric");
            query.setQueries(Collections.singleton(subQuery));

            try {
                List<QueryResponse> responseList = mmStore.query(query);
                long totalObservations = 0;
                for (QueryResponse r : responseList) {
                    totalObservations += r.getDps().size();
                }
                Assert.assertEquals("Unexpected number of total observations", x, totalObservations);

            } catch (TimelyException e) {
                e.printStackTrace();
            }

        }

    }

}