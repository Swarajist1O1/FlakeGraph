class DummyClass_21194 {
    @Test
    public void testSetOnPreferenceManagerGetOnPreference() {
        Preference preference = new Preference(mContext);
        mScreen.addPreference(preference);

        mManager.setPreferenceDataStore(mDataStore);

        assertEquals(mDataStore, preference.getPreferenceDataStore());
        assertNull(preference.getSharedPreferences());
    }

}