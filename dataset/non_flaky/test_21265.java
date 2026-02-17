class DummyClass_21265 {
    @Test
    public void bindViewHolder_singleLineTitleNotSet_shouldNotSetSingleLine() {
        PreferenceViewHolder holder = PreferenceViewHolder.createInstanceForTests(mViewGroup);
        mPreference.onBindViewHolder(holder);

        verify(mTitleView, never()).setSingleLine(anyBoolean());
    }

}