class DummyClass_21251 {
    @Test
    public void long_persistTwiceAndRetrieve_returnsSecondValue() {
        final long expected = 2;

        mPreference.putLong(1);
        mPreference.putLong(expected);
        long result = mPreference.getLong(-1);

        assertEquals(expected, result);
    }

}