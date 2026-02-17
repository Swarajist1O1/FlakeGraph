class DummyClass_21209 {
    @Test
    public void testGetIntWithDataStoreOnPref() {
        mPreference.setPreferenceDataStore(mDataStore);
        mScreen.addPreference(mPreference);

        mPreference.getInt(1);

        verify(mDataStore, atLeastOnce()).getInt(eq(KEY), eq(1));
    }

}