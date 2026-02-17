class DummyClass_21254 {
    @Test
    public void float_persistWhileDisabled_notPersisted() {
        mPreference.setPersistent(false);

        boolean wasPersisted = mPreference.putFloat(1);

        assertFalse(wasPersisted);
        assertEquals(-1, mSharedPref.getFloat(KEY, -1), FLOAT_PRECISION);
    }

}