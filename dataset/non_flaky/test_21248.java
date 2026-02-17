class DummyClass_21248 {
    @Test
    public void long_persist_getsStoredToSharedPrefs() {
        final long expected = 1;

        boolean wasPersisted = mPreference.putLong(expected);

        assertTrue(wasPersisted);
        assertEquals(expected, mSharedPref.getLong(KEY, -1));
    }

}