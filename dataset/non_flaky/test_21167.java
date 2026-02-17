class DummyClass_21167 {
    @Test
    public void createPreferenceGroupAdapter_displayTopLevelPreferences() {
        // No limit, should display all 10 preferences
        PreferenceGroupAdapter preferenceGroupAdapter = new PreferenceGroupAdapter(mScreen);
        assertPreferencesAreExpanded(preferenceGroupAdapter);

        // Limit > child count, should display all 10 preferences
        mScreen.setInitialExpandedChildrenCount(TOTAL_PREFERENCE + 4);
        preferenceGroupAdapter = new PreferenceGroupAdapter(mScreen);
        assertPreferencesAreExpanded(preferenceGroupAdapter);

        // Limit = child count, should display all 10 preferences
        mScreen.setInitialExpandedChildrenCount(TOTAL_PREFERENCE);
        preferenceGroupAdapter = new PreferenceGroupAdapter(mScreen);
        assertPreferencesAreExpanded(preferenceGroupAdapter);

        // Limit < child count, should display up to the limit + expand button
        mScreen.setInitialExpandedChildrenCount(INITIAL_EXPANDED_COUNT);
        preferenceGroupAdapter = new PreferenceGroupAdapter(mScreen);
        assertPreferencesAreCollapsed(preferenceGroupAdapter);
        for (int i = 0; i < INITIAL_EXPANDED_COUNT; i++) {
            assertEquals(mPreferenceList.get(i), preferenceGroupAdapter.getItem(i));
        }
        assertEquals(CollapsiblePreferenceGroupController.ExpandButton.class,
                preferenceGroupAdapter.getItem(INITIAL_EXPANDED_COUNT).getClass());
    }

}