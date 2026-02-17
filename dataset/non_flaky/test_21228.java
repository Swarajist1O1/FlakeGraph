class DummyClass_21228 {
    @Test
    public void expandablePreference_inCategory_collapsesCorrectly() {
        PreferenceCategory category = new PreferenceCategory(mContext);

        mScreen.addPreference(category);

        category.setKey("category");
        category.setInitialExpandedChildrenCount(1);

        category.addPreference(mPreference1);
        category.addPreference(mPreference2);
        category.addPreference(mPreference3);

        PreferenceGroupAdapter preferenceGroupAdapter = new PreferenceGroupAdapter(mScreen);

        assertEquals(3, preferenceGroupAdapter.getItemCount());

        assertEquals(category, preferenceGroupAdapter.getItem(0));
        assertEquals(mPreference1, preferenceGroupAdapter.getItem(1));
        assertEquals("Advanced", preferenceGroupAdapter.getItem(2).getTitle());
        assertEquals("Preference 2, Preference 3", preferenceGroupAdapter.getItem(2).getSummary());
    }

}