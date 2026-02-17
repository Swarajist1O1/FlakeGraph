class DummyClass_21171 {
    @Test
    public void clickExpandButton_shouldShowAllPreferences() {
        mScreen.setInitialExpandedChildrenCount(INITIAL_EXPANDED_COUNT);

        // First showing 5 preference with expand button
        PreferenceGroupAdapter preferenceGroupAdapter =
                PreferenceGroupAdapter.createInstanceWithCustomHandler(mScreen, mHandler);
        assertPreferencesAreCollapsed(preferenceGroupAdapter);

        // Click the expand button, should review all preferences
        final Preference expandButton = preferenceGroupAdapter.getItem(INITIAL_EXPANDED_COUNT);
        expandButton.performClick();
        assertPreferencesAreExpanded(preferenceGroupAdapter);
    }

}