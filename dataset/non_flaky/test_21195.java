class DummyClass_21195 {
    @Test
    public void testDataStoresHierarchy() {
        mPreference.setPreferenceDataStore(mDataStore);
        PreferenceDataStore secondaryDataStore = mock(PreferenceDataStore.class,
                Mockito.CALLS_REAL_METHODS);
        mManager.setPreferenceDataStore(secondaryDataStore);
        mScreen.addPreference(mPreference);

        mPreference.putString(TEST_STR);

        // Check that the Preference returns the correct data store.
        assertEquals(mDataStore, mPreference.getPreferenceDataStore());

        // Check that the secondary data store assigned to the manager was NOT used.
        verifyZeroInteractions(secondaryDataStore);

        // Check that the primary data store assigned directly to the preference was used.
        verify(mDataStore, atLeastOnce()).putString(eq(KEY), anyString());
    }

}