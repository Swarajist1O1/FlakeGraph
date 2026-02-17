class DummyClass_21250 {
    @Test
    public void long_persistAndRetrieve_returnsPersistedValue() {
        final long expected = 1;

        mPreference.putLong(expected);
        long result = mPreference.getLong(-1);

        assertEquals(expected, result);
    }

}