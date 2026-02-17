class DummyClass_21207 {
    @Test
    public void testPutIntWithDataStoreOnPref() {
        mPreference.setPreferenceDataStore(mDataStore);
        mScreen.addPreference(mPreference);
        putIntTestCommon();
    }

}