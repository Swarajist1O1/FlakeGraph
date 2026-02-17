class DummyClass_21266 {
    @Test
    public void bindViewHolder_singleLineTitleSetToTrue_shouldSetSingleLineToTrue() {
        PreferenceViewHolder holder = PreferenceViewHolder.createInstanceForTests(mViewGroup);
        mPreference.setSingleLineTitle(true);
        mPreference.onBindViewHolder(holder);

        verify(mTitleView).setSingleLine(true);
    }

}