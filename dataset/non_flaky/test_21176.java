class DummyClass_21176 {
    @Test
    public void restoreHierarchyState_previouslyExpanded_shouldRestoreToExpandedState() {
        PreferenceGroup.SavedState state =
                new PreferenceGroup.SavedState(
                        Preference.BaseSavedState.EMPTY_STATE, Integer.MAX_VALUE);
        // Initialized as collapsed, restore as expanded, should expand
        state.mInitialExpandedChildrenCount = Integer.MAX_VALUE;
        mScreen.setInitialExpandedChildrenCount(INITIAL_EXPANDED_COUNT);
        PreferenceGroupAdapter preferenceGroupAdapter =
                PreferenceGroupAdapter.createInstanceWithCustomHandler(mScreen, mHandler);
        mScreen.onRestoreInstanceState(state);
        verify(mHandler).sendMessageDelayed(any(Message.class), anyLong());
        assertPreferencesAreExpanded(preferenceGroupAdapter);
    }

}