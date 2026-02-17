class DummyClass_21211 {
    @Test
    public void testPutLongWithDataStoreOnPref() {
        mPreference.setPreferenceDataStore(mDataStore);
        mScreen.addPreference(mPreference);
        putLongTestCommon();
    }

}