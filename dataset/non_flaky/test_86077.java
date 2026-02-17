class DummyClass_86077 {
    @Test
    public void testDateRangeBucketWithOneTumblingWindow() {
        final long processingWindowSize = Duration.standardSeconds(60).getMillis();
        final long processingHopSize = Duration.standardSeconds(60).getMillis();
        final DateTime now = DateTime.now(DateTimeZone.UTC);
        final DateTime from = now;
        final DateTime to = now.plusMillis((int) processingWindowSize);
        TimeRange timeRange = AbsoluteRange.create(from, to);
        final DateRangeBucket rangeBucket = PivotAggregationSearch.buildDateRangeBuckets(timeRange, processingWindowSize, processingHopSize);

        assertThat(rangeBucket.ranges()).containsExactly(DateRange.create(from, to));
    }

}