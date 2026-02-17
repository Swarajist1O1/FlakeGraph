class DummyClass_21168 {
    @Test
    public void createPreferenceGroupAdapter_displayNestedPreferences() {
        final PreferenceScreen screen = mPreferenceManager.createPreferenceScreen(mContext);
        screen.setKey(PREFERENCE_KEY);
        final List<Preference> preferenceList = new ArrayList<>();

        // Add 2 preferences and 2 categories to screen
        createTestPreferences(screen, preferenceList, 2);
        createTestPreferencesCategory(screen, preferenceList, 4);
        createTestPreferencesCategory(screen, preferenceList, 4);

        // No limit, should display all 10 preferences + 2 categories
        PreferenceGroupAdapter preferenceGroupAdapter = new PreferenceGroupAdapter(screen);
        assertEquals(TOTAL_PREFERENCE + 2, preferenceGroupAdapter.getItemCount());

        // Limit > child count, should display all 10 preferences + 2 categories
        screen.setInitialExpandedChildrenCount(TOTAL_PREFERENCE + 4);
        preferenceGroupAdapter = new PreferenceGroupAdapter(screen);
        assertEquals(TOTAL_PREFERENCE + 2, preferenceGroupAdapter.getItemCount());

        // Limit = child count, should display all 10 preferences + 2 categories
        screen.setInitialExpandedChildrenCount(TOTAL_PREFERENCE);
        preferenceGroupAdapter = new PreferenceGroupAdapter(screen);
        assertEquals(TOTAL_PREFERENCE + 2, preferenceGroupAdapter.getItemCount());

        // Limit < child count, should display 2 preferences and the first 3 preference in the
        // category + expand button
        screen.setInitialExpandedChildrenCount(INITIAL_EXPANDED_COUNT);
        preferenceGroupAdapter = new PreferenceGroupAdapter(screen);
        assertEquals(INITIAL_EXPANDED_COUNT + 2, preferenceGroupAdapter.getItemCount());
        for (int i = 0; i <= INITIAL_EXPANDED_COUNT; i++) {
            assertEquals(preferenceList.get(i), preferenceGroupAdapter.getItem(i));
        }
        assertEquals(CollapsiblePreferenceGroupController.ExpandButton.class,
                preferenceGroupAdapter.getItem(INITIAL_EXPANDED_COUNT + 1).getClass());
    }

}