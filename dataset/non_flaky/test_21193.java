class DummyClass_21193 {
    @Test
    public void testSetGetOnPreferenceManager() {
        mManager.setPreferenceDataStore(mDataStore);

        assertEquals(mDataStore, mManager.getPreferenceDataStore());
        assertNull(mManager.getSharedPreferences());
    }

}