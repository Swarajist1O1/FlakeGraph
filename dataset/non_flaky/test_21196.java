class DummyClass_21196 {
    @Test
    public void testInitialValueIsFromDataStoreOnPreference() {
        when(mDataStore.getBoolean(anyString(), anyBoolean())).thenReturn(true);

        CheckBoxPreference pref = new CheckBoxPreference(mContext);
        pref.setKey("CheckboxTestPref");
        pref.setPreferenceDataStore(mDataStore);

        mScreen.addPreference(pref);

        assertTrue(pref.isChecked());
    }

}