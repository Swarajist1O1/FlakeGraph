class DummyClass_88868 {
    @Test
    public void testPredict() {
        Vector weights = new DenseVector(new double[] {2.0, 3.0});

        verifyPredict(getMdl(new LogisticRegressionModel(weights, 1.0).withRawLabels(true)));
    }

}