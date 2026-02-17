class DummyClass_21229 {
    @Test
    public void expandablePreference_inNestedCategory_collapsesCorrectly() {
        PreferenceCategory category = new PreferenceCategory(mContext);
        PreferenceCategory nestedCategory = new PreferenceCategory(mContext);

        mScreen.addPreference(category);
        category.addPreference(nestedCategory);

        nestedCategory.setKey("nested_category");
        nestedCategory.setInitialExpandedChildrenCount(1);

        nestedCategory.addPreference(mPreference1);
        nestedCategory.addPreference(mPreference2);
        nestedCategory.addPreference(mPreference3);

        PreferenceGroupAdapter preferenceGroupAdapter = new PreferenceGroupAdapter(mScreen);

        assertEquals(4, preferenceGroupAdapter.getItemCount());

        assertEquals(category, preferenceGroupAdapter.getItem(0));
        assertEquals(nestedCategory, preferenceGroupAdapter.getItem(1));
        assertEquals(mPreference1, preferenceGroupAdapter.getItem(2));
        assertEquals("Advanced", preferenceGroupAdapter.getItem(3).getTitle());
        assertEquals("Preference 2, Preference 3", preferenceGroupAdapter.getItem(3).getSummary());
    }

}