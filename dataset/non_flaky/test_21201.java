class DummyClass_21201 {
    @Test
    public void testGetStringWithDataStoreOnMgr() {
        mManager.setPreferenceDataStore(mDataStore);
        mScreen.addPreference(mPreference);

        mPreference.getString(TEST_STR);

        verify(mDataStore, atLeastOnce()).getString(eq(KEY), eq(TEST_STR));
    }

}