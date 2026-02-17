class DummyClass_21188 {
    @Test
    public void bindViewHolder_iconSpaceReserved_shouldReserveIconSpace() {
        PreferenceViewHolder holder = PreferenceViewHolder.createInstanceForTests(mViewGroup);
        mPreference.setIconSpaceReserved(true);
        mPreference.onBindViewHolder(holder);

        verify(mIconView).setVisibility(View.INVISIBLE);
        verify(mImageFrame).setVisibility(View.INVISIBLE);
    }

}