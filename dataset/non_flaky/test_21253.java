class DummyClass_21253 {
    @Test
    public void float_persist_getsStoredToSharedPrefs() {
        final float expected = 1;

        boolean wasPersisted = mPreference.putFloat(expected);

        assertTrue(wasPersisted);
        assertEquals(expected, mSharedPref.getFloat(KEY, -1), FLOAT_PRECISION);
    }

}