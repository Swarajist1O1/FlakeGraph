class DummyClass_86080 {
    @Test
    public void testDateRangeBucketWithCatchUpSlidingWindows() {
        final int processingWindowSizeSec = 120;
        final int processingHopSizeSec = 60;
        final DateTime now = DateTime.now(DateTimeZone.UTC);
        final DateTime from = now;
        // We are 3 full processingWindows behind
        final DateTime to = now.plusSeconds(processingWindowSizeSec * 3);
        TimeRange timeRange = AbsoluteRange.create(from, to);
        final DateRangeBucket rangeBucket = PivotAggregationSearch.buildDateRangeBuckets(timeRange, processingWindowSizeSec * 1000, processingHopSizeSec * 1000);

        assertThat(rangeBucket.ranges()).containsExactly(
                DateRange.create(from.plusSeconds(processingHopSizeSec * 0), from.plusSeconds(processingWindowSizeSec)),
                DateRange.create(from.plusSeconds(processingHopSizeSec * 1), from.plusSeconds(processingHopSizeSec * 1).plusSeconds(processingWindowSizeSec)),
                DateRange.create(from.plusSeconds(processingHopSizeSec * 2), from.plusSeconds(processingHopSizeSec * 2).plusSeconds(processingWindowSizeSec)),
                DateRange.create(from.plusSeconds(processingHopSizeSec * 3), from.plusSeconds(processingHopSizeSec * 3).plusSeconds(processingWindowSizeSec)),
                DateRange.create(from.plusSeconds(processingHopSizeSec * 4), to)
        );
    }

}