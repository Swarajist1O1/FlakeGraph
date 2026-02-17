class DummyClass_21249 {
    @Test
    public void long_persistWhileDisabled_notPersisted() {
        mPreference.setPersistent(false);

        boolean wasPersisted = mPreference.putLong(1);

        assertFalse(wasPersisted);
        assertEquals(-1, mSharedPref.getLong(KEY, -1));
    }

}