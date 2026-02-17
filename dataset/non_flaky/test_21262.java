class DummyClass_21262 {
    @Test
    public void parentAddRemoveTest() {
        PreferenceManager manager = new PreferenceManager(mContext);

        PreferenceScreen screen = manager.createPreferenceScreen(mContext);
        assertNull(screen.getParent());

        PreferenceCategory category = new PreferenceCategory(mContext);
        assertNull(category.getParent());

        CheckBoxPreference pref = new CheckBoxPreference(mContext);
        assertNull(pref.getParent());

        screen.addPreference(category);
        assertEquals(screen, category.getParent());

        category.addPreference(pref);
        assertEquals(category, pref.getParent());

        screen.removePreference(category);
        assertNull(category.getParent());

        category.removePreference(pref);
        assertNull(pref.getParent());
    }

}