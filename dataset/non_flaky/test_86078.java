class DummyClass_86078 {
    @Test
    public void testDateRangeBucketWithCatchUpTumblingWindows() {
        final long processingWindowSize = Duration.standardSeconds(60).getMillis();
        final long processingHopSize = Duration.standardSeconds(60).getMillis();
        final DateTime now = DateTime.now(DateTimeZone.UTC);
        final DateTime from = now;
        // We are 3 full processingWindows behind
        final DateTime to = now.plusMillis((int) processingWindowSize * 3);
        TimeRange timeRange = AbsoluteRange.create(from, to);
        final DateRangeBucket rangeBucket = PivotAggregationSearch.buildDateRangeBuckets(timeRange, processingWindowSize, processingHopSize);

        assertThat(rangeBucket.ranges()).containsExactly(
                DateRange.create(from.plusMillis((int) (processingWindowSize * 0)), from.plusMillis((int) (processingWindowSize * 1))),
                DateRange.create(from.plusMillis((int) (processingWindowSize * 1)), from.plusMillis((int) (processingWindowSize * 2))),
                DateRange.create(from.plusMillis((int) (processingWindowSize * 2)), from.plusMillis((int) (processingWindowSize * 3)))
        );
    }

}