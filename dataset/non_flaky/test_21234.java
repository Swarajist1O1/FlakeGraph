class DummyClass_21234 {
    @Test
    public void string_persistWhileDisabled_notPersisted() {
        mPreference.setPersistent(false);

        boolean wasPersisted = mPreference.putString("Test");

        assertFalse(wasPersisted);
        assertNull(mSharedPref.getString(KEY, null));
    }

}