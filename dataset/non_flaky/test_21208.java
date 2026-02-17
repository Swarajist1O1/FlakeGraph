class DummyClass_21208 {
    @Test
    public void testPutIntWithDataStoreOnMgr() {
        mManager.setPreferenceDataStore(mDataStore);
        mScreen.addPreference(mPreference);
        putIntTestCommon();
    }

}