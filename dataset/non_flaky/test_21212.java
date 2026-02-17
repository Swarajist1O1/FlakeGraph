class DummyClass_21212 {
    @Test
    public void testPutLongWithDataStoreOnMgr() {
        mManager.setPreferenceDataStore(mDataStore);
        mScreen.addPreference(mPreference);
        putLongTestCommon();
    }

}