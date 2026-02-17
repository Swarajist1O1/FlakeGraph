class DummyClass_21206 {
    @Test
    public void testGetStringSetWithDataStoreOnMgr() {
        mManager.setPreferenceDataStore(mDataStore);
        mScreen.addPreference(mPreference);
        Set<String> testSet = new HashSet<>();

        mPreference.getStringSet(testSet);

        verify(mDataStore, atLeastOnce()).getStringSet(eq(KEY), eq(testSet));
    }

}