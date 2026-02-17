class DummyClass_21210 {
    @Test
    public void testGetIntWithDataStoreOnMgr() {
        mManager.setPreferenceDataStore(mDataStore);
        mScreen.addPreference(mPreference);

        mPreference.getInt(1);

        verify(mDataStore, atLeastOnce()).getInt(eq(KEY), eq(1));
    }

}