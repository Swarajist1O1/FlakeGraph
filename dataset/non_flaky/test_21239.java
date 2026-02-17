class DummyClass_21239 {
    @Test
    public void stringSet_persistWhileDisabled_notPersisted() {
        mPreference.setPersistent(false);

        boolean wasPersisted = mPreference.putStringSet(TEST_STR_SET);

        assertFalse(wasPersisted);
        assertNull(mSharedPref.getString(KEY, null));
    }

}