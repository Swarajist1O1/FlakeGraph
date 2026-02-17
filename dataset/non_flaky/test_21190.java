class DummyClass_21190 {
    @Test
    public void bindViewHolder_hasIcon_shouldDisplayIcon() {
        PreferenceViewHolder holder = PreferenceViewHolder.createInstanceForTests(mViewGroup);
        mPreference.setIcon(new ColorDrawable(Color.BLACK));
        mPreference.onBindViewHolder(holder);

        verify(mIconView).setVisibility(View.VISIBLE);
        verify(mImageFrame).setVisibility(View.VISIBLE);
    }

}