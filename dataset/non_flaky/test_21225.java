class DummyClass_21225 {
    @Test
    public void testSharedPrefNullIfWithDS() {
        mScreen.addPreference(mPreference);

        mPreference.setPreferenceDataStore(mDataStore);

        assertNull(mPreference.getSharedPreferences());
    }

}