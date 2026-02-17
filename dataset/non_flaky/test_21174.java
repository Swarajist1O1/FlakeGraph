class DummyClass_21174 {
    @Test
    public void restoreInstanceState_noChange_shouldDoNothing() {
        PreferenceGroup.SavedState state;

        // Initialized as expanded, restore as expanded, should remain expanded
        state = new PreferenceGroup.SavedState(
                Preference.BaseSavedState.EMPTY_STATE, Integer.MAX_VALUE);
        PreferenceGroupAdapter preferenceGroupAdapter =
                PreferenceGroupAdapter.createInstanceWithCustomHandler(mScreen, mHandler);
        mScreen.onRestoreInstanceState(state);
        assertPreferencesAreExpanded(preferenceGroupAdapter);
        verify(mHandler, never()).sendMessageDelayed(any(Message.class), anyLong());

        // Initialized as collapsed, restore as collapsed, should remain collapsed
        mScreen.setInitialExpandedChildrenCount(INITIAL_EXPANDED_COUNT);
        state = new PreferenceGroup.SavedState(
                Preference.BaseSavedState.EMPTY_STATE, INITIAL_EXPANDED_COUNT);
        preferenceGroupAdapter =
                PreferenceGroupAdapter.createInstanceWithCustomHandler(mScreen, mHandler);
        mScreen.onRestoreInstanceState(state);
        assertPreferencesAreCollapsed(preferenceGroupAdapter);
        verify(mHandler, never()).sendMessageDelayed(any(Message.class), anyLong());
    }

}