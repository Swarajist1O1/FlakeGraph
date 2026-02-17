class DummyClass_21191 {
    @Test
    public void testThatDataStoreIsNullByDefault() {
        Preference preference = new Preference(mContext);
        mScreen.addPreference(preference);

        assertNull(preference.getPreferenceDataStore());
        assertNotNull(preference.getSharedPreferences());

        assertNull(mManager.getPreferenceDataStore());
        assertNotNull(mManager.getSharedPreferences());
    }

}