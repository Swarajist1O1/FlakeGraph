class DummyClass_21258 {
    @Test
    public void boolean_persist_getsStoredToSharedPrefs() {
        final boolean expected = true;

        boolean wasPersisted = mPreference.putBoolean(expected);

        assertTrue(wasPersisted);
        assertEquals(expected, mSharedPref.getBoolean(KEY, !expected));
    }

}