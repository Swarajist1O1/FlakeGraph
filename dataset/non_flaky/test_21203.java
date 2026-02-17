class DummyClass_21203 {
    @Test
    public void testPutStringSetWithDataStoreOnPref() {
        mPreference.setPreferenceDataStore(mDataStore);
        mScreen.addPreference(mPreference);
        putStringSetTestCommon();
    }

}