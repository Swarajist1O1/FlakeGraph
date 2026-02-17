class DummyClass_21202 {
    @Test
    public void testDefaultStringValue() {
        mPreference.setPreferenceDataStore(mDataStore);
        mPreference.setDefaultValue(TEST_DEFAULT_STR);
        mSharedPref.edit().putString(KEY, TEST_WRONG_STR).commit();
        mScreen.addPreference(mPreference);
        mSharedPref.edit().remove(KEY).commit();
        assertEquals(TEST_DEFAULT_STR, mPreference.getDefaultValue());
    }

}