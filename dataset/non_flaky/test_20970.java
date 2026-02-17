class DummyClass_20970 {
    @Test
    public void testOne() throws TimelyException {

        long now = System.currentTimeMillis();
        DataStoreCache mmStore = getMetricMemoryStore1(now);

        QueryRequest query = new QueryRequest();
        query.setStart(now);
        query.setEnd(now + 100000);
        query.setMsResolution(true);
        QueryRequest.SubQuery subQuery = new QueryRequest.SubQuery();
        subQuery.setDownsample(Optional.of("5s-avg"));
        subQuery.setMetric("mymetric");
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