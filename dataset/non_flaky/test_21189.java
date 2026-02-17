class DummyClass_21189 {
    @Test
    public void bindViewHolder_iconSpaceNotReserved_shouldNotReserveIconSpace() {
        PreferenceViewHolder holder = PreferenceViewHolder.createInstanceForTests(mViewGroup);
        mPreference.setIconSpaceReserved(false);
        mPreference.onBindViewHolder(holder);

        verify(mIconView).setVisibility(View.GONE);
        verify(mImageFrame).setVisibility(View.GONE);
    }

}