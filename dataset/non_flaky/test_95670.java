class DummyClass_95670 {
    @Test(expected = IllegalStateException.class)
    public void testIsActiveThrowsWhenNoApplicationContext() {
        FeatureState featureState = new FeatureState(TestFeatures.FEATURE_ONE, true);

        ContextClassLoaderApplicationContextHolder.release();

        strategy.isActive(featureState, null);
    }

}