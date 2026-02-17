class DummyClass_21260 {
    @Test
    public void boolean_persistAndRetrieve_returnsPersistedValue() {
        final boolean expected = true;

        mPreference.putBoolean(expected);
        boolean result = mPreference.getBoolean(!expected);

        assertEquals(expected, result);
    }

}