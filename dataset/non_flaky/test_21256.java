class DummyClass_21256 {
    @Test
    public void float_persistTwiceAndRetrieve_returnsSecondValue() {
        final float expected = 2;

        mPreference.putFloat(1);
        mPreference.putFloat(expected);
        float result = mPreference.getFloat(-1);

        assertEquals(expected, result, FLOAT_PRECISION);
    }

}