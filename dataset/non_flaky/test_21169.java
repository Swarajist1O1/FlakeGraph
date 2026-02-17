class DummyClass_21169 {
    @Test
    public void createPreferenceGroupAdapter_setExpandButtonSummary() {
        mScreen.setInitialExpandedChildrenCount(INITIAL_EXPANDED_COUNT);
        PreferenceGroupAdapter preferenceGroupAdapter = new PreferenceGroupAdapter(mScreen);
        // Preference 5 to Preference 9 are collapsed
        CharSequence summary = mPreferenceList.get(INITIAL_EXPANDED_COUNT).getTitle();
        for (int i = INITIAL_EXPANDED_COUNT + 1; i < TOTAL_PREFERENCE; i++) {
            summary = mContext.getString(R.string.summary_collapsed_preference_list,
                    summary, mPreferenceList.get(i).getTitle());
        }
        final Preference expandButton = preferenceGroupAdapter.getItem(INITIAL_EXPANDED_COUNT);
        assertEquals(summary, expandButton.getSummary());
    }

}