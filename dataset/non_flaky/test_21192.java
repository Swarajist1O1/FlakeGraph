class DummyClass_21192 {
    @Test
    public void testSetGetOnPreference() {
        Preference preference = new Preference(mContext);

        preference.setPreferenceDataStore(mDataStore);

        assertEquals(mDataStore, preference.getPreferenceDataStore());
    }

}