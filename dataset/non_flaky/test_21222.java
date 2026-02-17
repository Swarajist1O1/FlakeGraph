class DummyClass_21222 {
    @Test
    public void testGetBooleanWithDataStoreOnMgr() {
        mManager.setPreferenceDataStore(mDataStore);
        mScreen.addPreference(mPreference);

        mPreference.getBoolean(true);

        verify(mDataStore, atLeastOnce()).getBoolean(eq(KEY), eq(true));
    }

}