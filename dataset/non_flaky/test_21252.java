class DummyClass_21252 {
    @Test
    public void float_retrieveWhenEmpty_returnsDefault() {
        assertEquals(1, mPreference.getFloat(1), FLOAT_PRECISION);
    }

}