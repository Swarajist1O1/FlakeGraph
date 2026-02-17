class DummyClass_86057 {
    @Test
    public void testFunctionMapping() {
        testToSeriesSpec(AggregationFunction.AVG, Average.class);
        testToSeriesSpec(AggregationFunction.CARD, Cardinality.class);
        testToSeriesSpec(AggregationFunction.COUNT, Count.class);
        testToSeriesSpec(AggregationFunction.MAX, Max.class);
        testToSeriesSpec(AggregationFunction.MIN, Min.class);
        testToSeriesSpec(AggregationFunction.STDDEV, StdDev.class);
        testToSeriesSpec(AggregationFunction.SUM, Sum.class);
        testToSeriesSpec(AggregationFunction.SUMOFSQUARES, SumOfSquares.class);
        testToSeriesSpec(AggregationFunction.VARIANCE, Variance.class);
    }

}