class DummyClass_21217 {
    @Test
    public void testGetFloatWithDataStoreOnPref() {
        mPreference.setPreferenceDataStore(mDataStore);
        mScreen.addPreference(mPreference);

        mPreference.getFloat(1f);

        verify(mDataStore, atLeastOnce()).getFloat(eq(KEY), eq(1f));
    }

}