class DummyClass_21231 {
    @Test(expected = IllegalArgumentException.class)
    public void nestedExpandablePreferences_notAllowed_shouldThrowAnException() {
        PreferenceCategory category = new PreferenceCategory(mContext);
        PreferenceCategory nestedCategory = new PreferenceCategory(mContext);

        mScreen.addPreference(category);
        category.addPreference(nestedCategory);

        category.setKey("category");
        category.setInitialExpandedChildrenCount(1);

        nestedCategory.setKey("nested_category");
        nestedCategory.setInitialExpandedChildrenCount(1);

        // Trying to nest expandable preferences should throw an exception
        new PreferenceGroupAdapter(mScreen);
    }

}