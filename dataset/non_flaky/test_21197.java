class DummyClass_21197 {
    @Test
    public void testInitialValueIsFromDataStoreOnPreferenceManager() {
        when(mDataStore.getBoolean(anyString(), anyBoolean())).thenReturn(true);

        mManager.setPreferenceDataStore(mDataStore);
        CheckBoxPreference pref = new CheckBoxPreference(mContext);
        pref.setKey("CheckboxTestPref");

        mScreen.addPreference(pref);

        assertTrue(pref.isChecked());
    }

}