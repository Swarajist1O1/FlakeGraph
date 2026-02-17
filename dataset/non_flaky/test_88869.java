class DummyClass_88869 {
    @Test
    public void testTrainWithTheLinearlySeparableCase() {
        Map<Integer, Double[]> cacheMock = new HashMap<>();

        for (int i = 0; i < twoLinearlySeparableClasses.length; i++) {
            double[] row = twoLinearlySeparableClasses[i];
            Double[] convertedRow = new Double[row.length];
            for (int j = 0; j < row.length; j++)
                convertedRow[j] = row[j];
            cacheMock.put(i, convertedRow);
        }

        LogisticRegressionSGDTrainer<?> trainer = new LogisticRegressionSGDTrainer<>()
            .withUpdatesStgy(new UpdatesStrategy<>(new SimpleGDUpdateCalculator(0.2),
                SimpleGDParameterUpdate::sumLocal, SimpleGDParameterUpdate::avg))
            .withMaxIterations(100000)
            .withLocIterations(100)
            .withBatchSize(10)
            .withSeed(123L);

        PipelineMdl<Integer, Double[]> mdl = new Pipeline<Integer, Double[], Vector>()
            .addFeatureExtractor((k, v) -> VectorUtils.of(Arrays.copyOfRange(v, 1, v.length)))
            .addLabelExtractor((k, v) -> v[0])
            .addPreprocessor(new MinMaxScalerTrainer<Integer, Object[]>())
            .addPreprocessor(new NormalizationTrainer<Integer, Object[]>()
                .withP(1))
            .addTrainer(trainer)
            .fit(
                cacheMock,
                parts
            );

        TestUtils.assertEquals(0, mdl.apply(VectorUtils.of(100, 10)), PRECISION);
        TestUtils.assertEquals(1, mdl.apply(VectorUtils.of(10, 100)), PRECISION);
    }

}