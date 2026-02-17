class DummyClass_21226 {
    @Test
    public void testSharedPrefNullIfWithDSMgr() {
        mManager.setPreferenceDataStore(mDataStore);

        assertNull(mManager.getSharedPreferences());
    }

}