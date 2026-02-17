class DummyClass_21204 {
    @Test
    public void testPutStringSetWithDataStoreOnMgr() {
        mManager.setPreferenceDataStore(mDataStore);
        mScreen.addPreference(mPreference);
        putStringSetTestCommon();
    }

}