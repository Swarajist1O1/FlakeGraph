class DummyClass_21216 {
    @Test
    public void testPutFloatWithDataStoreOnMgr() {
        mManager.setPreferenceDataStore(mDataStore);
        mScreen.addPreference(mPreference);
        putFloatTestCommon();
    }

}