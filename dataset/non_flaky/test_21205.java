class DummyClass_21205 {
    @Test
    public void testGetStringSetWithDataStoreOnPref() {
        mPreference.setPreferenceDataStore(mDataStore);
        mScreen.addPreference(mPreference);
        Set<String> testSet = new HashSet<>();

        mPreference.getStringSet(testSet);

        verify(mDataStore, atLeastOnce()).getStringSet(eq(KEY), eq(testSet));
    }

}