class DummyClass_21219 {
    @Test
    public void testPutBooleanWithDataStoreOnPref() {
        mPreference.setPreferenceDataStore(mDataStore);
        mScreen.addPreference(mPreference);
        putBooleanTestCommon();
    }

}