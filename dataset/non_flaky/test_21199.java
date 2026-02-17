class DummyClass_21199 {
    @Test
    public void testPutStringWithDataStoreOnMgr() {
        mManager.setPreferenceDataStore(mDataStore);
        mScreen.addPreference(mPreference);
        putStringTestCommon();
    }

}