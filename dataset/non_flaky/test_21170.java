class DummyClass_21170 {
    @Test
    public void createPreferenceGroupAdapter_expandButtonSummaryShouldListVisiblePreferencesOnly() {
        mScreen.setInitialExpandedChildrenCount(INITIAL_EXPANDED_COUNT);
        mPreferenceList.get(INITIAL_EXPANDED_COUNT + 1).setVisible(false);
        mPreferenceList.get(INITIAL_EXPANDED_COUNT + 4).setVisible(false);
        PreferenceGroupAdapter preferenceGroupAdapter = new PreferenceGroupAdapter(mScreen);
        // Preference 5 to Preference 9 are collapsed, only preferences 5, 7, 8 are visible
        CharSequence summary = mPreferenceList.get(INITIAL_EXPANDED_COUNT).getTitle();
        summary = mContext.getString(R.string.summary_collapsed_preference_list,
                summary, mPreferenceList.get(INITIAL_EXPANDED_COUNT + 2).getTitle());
        summary = mContext.getString(R.string.summary_collapsed_preference_list,
                summary, mPreferenceList.get(INITIAL_EXPANDED_COUNT + 3).getTitle());
        final Preference expandButton = preferenceGroupAdapter.getItem(INITIAL_EXPANDED_COUNT);
        assertEquals(summary, expandButton.getSummary());
    }

}