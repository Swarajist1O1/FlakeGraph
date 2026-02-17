class DummyClass_21233 {
    @Test
    public void string_persist_getsStoredToSharedPrefs() {
        final String expected = "Test";

        boolean wasPersisted = mPreference.putString(expected);

        assertTrue(wasPersisted);
        assertEquals(expected, mSharedPref.getString(KEY, null));
    }

}