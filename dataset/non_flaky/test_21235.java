class DummyClass_21235 {
    @Test
    public void string_persistAndRetrieve_returnsPersistedValue() {
        final String expected = "Test";

        mPreference.putString(expected);
        String result = mPreference.getString("Default");

        assertEquals(expected, result);
    }

}