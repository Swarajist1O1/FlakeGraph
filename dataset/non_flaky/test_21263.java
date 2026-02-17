class DummyClass_21263 {
    @Test
    public void parentReassignTest() {
        PreferenceManager manager = new PreferenceManager(mContext);

        PreferenceScreen screen = manager.createPreferenceScreen(mContext);

        PreferenceCategory category1 = new PreferenceCategory(mContext);
        screen.addPreference(category1);
        PreferenceCategory category2 = new PreferenceCategory(mContext);
        screen.addPreference(category2);

        CheckBoxPreference pref = new CheckBoxPreference(mContext);
        assertNull(pref.getParent());

        category1.addPreference(pref);
        assertEquals(category1, pref.getParent());

        category1.removePreference(pref);
        category2.addPreference(pref);
        assertEquals(category2, pref.getParent());
    }

}