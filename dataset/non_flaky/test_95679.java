class DummyClass_95679 {
    @Test
    public void testActiveByDefault() {

        // should be true by default
        assertTrue(MyFeatures.FEATURE_ONE.isActive());

        // second result should be the same
        assertTrue(MyFeatures.FEATURE_ONE.isActive());

    }

}