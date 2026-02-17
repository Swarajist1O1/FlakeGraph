class DummyClass_21173 {
    @Test
    public void saveInstanceState_shouldSaveMaxNumberOfChildrenToShow() {
        // No limit set, should save max value
        Parcelable state = mScreen.onSaveInstanceState();
        assertEquals(PreferenceGroup.SavedState.class, state.getClass());
        assertEquals(Integer.MAX_VALUE,
                ((PreferenceGroup.SavedState) state).mInitialExpandedChildrenCount);

        // Has limit set, should save limit
        mScreen.setInitialExpandedChildrenCount(INITIAL_EXPANDED_COUNT);
        state = mScreen.onSaveInstanceState();
        assertEquals(PreferenceGroup.SavedState.class, state.getClass());
        assertEquals(INITIAL_EXPANDED_COUNT,
                ((PreferenceGroup.SavedState) state).mInitialExpandedChildrenCount);
    }

}