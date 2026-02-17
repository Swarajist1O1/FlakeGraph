class DummyClass_21214 {
    @Test
    public void testGetLongWithDataStoreOnMgr() {
        mManager.setPreferenceDataStore(mDataStore);
        mScreen.addPreference(mPreference);

        mPreference.getLong(1L);

        verify(mDataStore, atLeastOnce()).getLong(eq(KEY), eq(1L));
    }

}