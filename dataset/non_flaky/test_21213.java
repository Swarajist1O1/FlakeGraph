class DummyClass_21213 {
    @Test
    public void testGetLongWithDataStoreOnPref() {
        mPreference.setPreferenceDataStore(mDataStore);
        mScreen.addPreference(mPreference);

        mPreference.getLong(1L);

        verify(mDataStore, atLeastOnce()).getLong(eq(KEY), eq(1L));
    }

}