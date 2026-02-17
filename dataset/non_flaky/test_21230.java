class DummyClass_21230 {
    @Test
    public void expandablePreference_inCategoryContainingAnotherCategory_collapsesCorrectly() {
        PreferenceCategory category = new PreferenceCategory(mContext);
        PreferenceCategory nestedCategory = new PreferenceCategory(mContext);

        mScreen.addPreference(category);

        category.setKey("nested_category");
        category.setInitialExpandedChildrenCount(1);

        category.addPreference(mPreference1);
        category.addPreference(nestedCategory);
        nestedCategory.addPreference(mPreference2);
        nestedCategory.addPreference(mPreference3);

        PreferenceGroupAdapter preferenceGroupAdapter = new PreferenceGroupAdapter(mScreen);

        assertEquals(3, preferenceGroupAdapter.getItemCount());

        assertEquals(category, preferenceGroupAdapter.getItem(0));
        assertEquals(mPreference1, preferenceGroupAdapter.getItem(1));
        assertEquals("Advanced", preferenceGroupAdapter.getItem(2).getTitle());
        assertEquals("Preference 2, Preference 3", preferenceGroupAdapter.getItem(2).getSummary());

        // If the nested category has a title, display that in the summary instead of the children
        final String title = "Category";
        nestedCategory.setTitle(title);

        preferenceGroupAdapter = new PreferenceGroupAdapter(mScreen);

        assertEquals(3, preferenceGroupAdapter.getItemCount());

        assertEquals(category, preferenceGroupAdapter.getItem(0));
        assertEquals(mPreference1, preferenceGroupAdapter.getItem(1));
        assertEquals("Advanced", preferenceGroupAdapter.getItem(2).getTitle());
        assertEquals(title, preferenceGroupAdapter.getItem(2).getSummary());
    }

}