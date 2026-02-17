class DummyClass_21255 {
    @Test
    public void float_persistAndRetrieve_returnsPersistedValue() {
        final float expected = 1;

        mPreference.putFloat(expected);
        float result = mPreference.getFloat(-1);

        assertEquals(expected, result, FLOAT_PRECISION);
    }

}