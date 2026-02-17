class DummyClass_21245 {
    @Test
    public void int_persistAndRetrieve_returnsPersistedValue() {
        final int expected = 1;

        mPreference.putInt(expected);
        int result = mPreference.getInt(-1);

        assertEquals(expected, result);
    }

}