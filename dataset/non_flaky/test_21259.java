class DummyClass_21259 {
    @Test
    public void boolean_persistWhileDisabled_notPersisted() {
        mPreference.setPersistent(false);

        boolean wasPersisted = mPreference.putBoolean(true);

        assertFalse(wasPersisted);
        assertEquals(false, mSharedPref.getBoolean(KEY, false));
    }

}