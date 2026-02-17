class DummyClass_21243 {
    @Test
    public void int_persist_getsStoredToSharedPrefs() {
        final int expected = 1;

        boolean wasPersisted = mPreference.putInt(expected);

        assertTrue(wasPersisted);
        assertEquals(expected, mSharedPref.getInt(KEY, -1));
    }

}