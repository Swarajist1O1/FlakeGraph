class DummyClass_21218 {
    @Test
    public void testGetFloatWithDataStoreOnMgr() {
        mManager.setPreferenceDataStore(mDataStore);
        mScreen.addPreference(mPreference);

        mPreference.getFloat(1f);

        verify(mDataStore, atLeastOnce()).getFloat(eq(KEY), eq(1f));
    }

}