class DummyClass_21215 {
    @Test
    public void testPutFloatWithDataStoreOnPref() {
        mPreference.setPreferenceDataStore(mDataStore);
        mScreen.addPreference(mPreference);
        putFloatTestCommon();
    }

}