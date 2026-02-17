class DummyClass_21198 {
    @Test
    public void testPutStringWithDataStoreOnPref() {
        mPreference.setPreferenceDataStore(mDataStore);
        mScreen.addPreference(mPreference);
        putStringTestCommon();
    }

}