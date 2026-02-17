class DummyClass_20967 {
    @Test
    public void testRateIterator() throws TimelyException {

        long BASETIME = System.currentTimeMillis();
        // align basetime to a downsample period
        BASETIME = BASETIME - (BASETIME % 1000);
        DataStoreCache mmStore = getMetricMemoryStore2(BASETIME);

        QueryRequest query = new QueryRequest();
        query.setStart(BASETIME);
        query.setEnd(BASETIME + 1440000);
        query.setMsResolution(true);
        QueryRequest.SubQuery subQuery = new QueryRequest.SubQuery();
        subQuery.setDownsample(Optional.of("1ms-avg"));
        subQuery.setMetric("metric.number.1");
        subQuery.addTag("host", ".*");
        QueryRequest.RateOption rateOption = new QueryRequest.RateOption();
        rateOption.setCounter(false);
        subQuery.setRate(true);
        subQuery.setRateOptions(rateOption);
        query.setQueries(Collections.singleton(subQuery));

        int x = 0;
        SortedKeyValueIterator<org.apache.accumulo.core.data.Key, org.apache.accumulo.core.data.Value> itr = null;
        try {
            // long firstTimestamp = Long.MAX_VALUE;
            long firstTimestamp = -1;
            long lastTimestamp = -1;
            int numSamples = 0;
            itr = mmStore.setupIterator(query, subQuery, new Authorizations(), Long.MAX_VALUE);
            while (itr.hasTop()) {
                itr.next();
                Map<Set<Tag>, Aggregation> aggregations = AggregationIterator.decodeValue(itr.getTopValue());
                for (Map.Entry<Set<Tag>, Aggregation> entry : aggregations.entrySet()) {
                    for (Sample s : entry.getValue()) {
                        numSamples++;
                        if (firstTimestamp == -1) {
                            firstTimestamp = s.timestamp;
                        }
                        lastTimestamp = s.timestamp;
                        // if (s.timestamp < firstTimestamp) {
                        // firstTimestamp = s.timestamp;
                        // }
                        // if (s.timestamp > lastTimestamp) {
                        // lastTimestamp = s.timestamp;
                        // }
                    }
                }
            }
            Assert.assertEquals("First timestamp incorrect", BASETIME + 1000, firstTimestamp);
            Assert.assertEquals("Last timestamp incorrect", BASETIME + 1440000, lastTimestamp);
            Assert.assertEquals("Number of samples incorrect", 2880, numSamples);
        } catch (IOException | ClassNotFoundException e) {
            LOG.error("exception in test", e);
        }
    }

}