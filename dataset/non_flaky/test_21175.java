class DummyClass_21175 {
    @Test
    public void restoreHierarchyState_previouslyCollapsed_shouldRestoreToCollapsedState() {
        PreferenceGroup.SavedState state =
                new PreferenceGroup.SavedState(
                        Preference.BaseSavedState.EMPTY_STATE, Integer.MAX_VALUE);
        // Initialized as expanded, restore as collapsed, should collapse
        state.mInitialExpandedChildrenCount = INITIAL_EXPANDED_COUNT;
        mScreen.setInitialExpandedChildrenCount(Integer.MAX_VALUE);
        PreferenceGroupAdapter preferenceGroupAdapter =
                PreferenceGroupAdapter.createInstanceWithCustomHandler(mScreen, mHandler);
        mScreen.onRestoreInstanceState(state);
        verify(mHandler).sendMessageDelayed(any(Message.class), anyLong());
        assertPreferencesAreCollapsed(preferenceGroupAdapter);
    }

}