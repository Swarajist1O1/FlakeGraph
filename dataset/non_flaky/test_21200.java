class DummyClass_21200 {
    @Test
    public void testGetStringWithDataStoreOnPref() {
        mPreference.setPreferenceDataStore(mDataStore);
        mScreen.addPreference(mPreference);

        mPreference.getString(TEST_STR);

        verify(mDataStore, atLeastOnce()).getString(eq(KEY), eq(TEST_STR));
    }

}