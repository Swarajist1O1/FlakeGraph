class DummyClass_20971 {
    @Test
    public void testStorage() throws TimelyException {

        long now = System.currentTimeMillis();
        DataStoreCache mmStore = getMetricMemoryStore2(now);

        QueryRequest query = new QueryRequest();
        query.setStart(now);
        query.setEnd(now + 86400000);
        query.setMsResolution(true);
        QueryRequest.SubQuery subQuery = new QueryRequest.SubQuery();
        subQuery.setDownsample(Optional.of("5m-avg"));
        subQuery.setMetric("metric.number.1");
        subQuery.addTag("host", ".*");
        query.setQueries(Collections.singleton(subQuery));

        try {
            List<QueryResponse> responseList = mmStore.query(query);
            for (QueryResponse response : responseList) {
                System.out.println(response.toString());
            }
        } catch (TimelyException e) {
            e.printStackTrace();
        }
    }

}