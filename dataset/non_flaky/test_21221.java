class DummyClass_21221 {
    @Test
    public void testGetBooleanWithDataStoreOnPref() {
        mPreference.setPreferenceDataStore(mDataStore);
        mScreen.addPreference(mPreference);

        mPreference.getBoolean(true);

        verify(mDataStore, atLeastOnce()).getBoolean(eq(KEY), eq(true));
    }

}