class DummyClass_21172 {
    @Test
    public void onPreferenceVisibilityChange_shouldSyncPreferencesIfCollapsed() {
        // No limit set, should not sync preference
        PreferenceGroupAdapter preferenceGroupAdapter =
                PreferenceGroupAdapter.createInstanceWithCustomHandler(mScreen, mHandler);
        preferenceGroupAdapter.onPreferenceVisibilityChange(mPreferenceList.get(3));
        verify(mHandler, never()).sendMessageDelayed(any(Message.class), anyLong());

        // Has limit set, should sync preference
        mScreen.setInitialExpandedChildrenCount(INITIAL_EXPANDED_COUNT);
        preferenceGroupAdapter =
                PreferenceGroupAdapter.createInstanceWithCustomHandler(mScreen, mHandler);
        preferenceGroupAdapter.onPreferenceVisibilityChange(mPreferenceList.get(3));
        verify(mHandler).sendMessageDelayed(any(Message.class), anyLong());

        // Preferences expanded already, should not sync preference
        final Preference expandButton = preferenceGroupAdapter.getItem(INITIAL_EXPANDED_COUNT);
        expandButton.performClick();
        reset(mHandler);
        preferenceGroupAdapter.onPreferenceVisibilityChange(mPreferenceList.get(3));
        verify(mHandler, never()).sendMessageDelayed(any(Message.class), anyLong());
    }

}