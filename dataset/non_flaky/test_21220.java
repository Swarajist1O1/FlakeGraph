class DummyClass_21220 {
    @Test
    public void testPutBooleanWithDataStoreOnMgr() {
        mManager.setPreferenceDataStore(mDataStore);
        mScreen.addPreference(mPreference);
        putBooleanTestCommon();
    }

}