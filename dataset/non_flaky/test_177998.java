class DummyClass_177998 {
    @Test
    public void parallaxTest() throws Throwable {
        final int mDefaultVerticalOffset = -300;
        Intent intent = new Intent();
        intent.putExtra(DetailsTestFragment.VERTICAL_OFFSET, mDefaultVerticalOffset);
        mActivity = activityTestRule.launchActivity(intent);

        final DetailsTestFragment detailsFragment = mActivity.getDetailsFragment();
        DetailsBackgroundParallaxHelper parallaxHelper = detailsFragment.getParallaxHelper();
        final CompositeDrawable drawable = (CompositeDrawable) parallaxHelper.getDrawable();
        final FitWidthBitmapDrawable bitmapDrawable = (FitWidthBitmapDrawable)
                (drawable.getChildAt(0).getDrawable());

        PollingCheck.waitFor(4000, new PollingCheck.PollingCheckCondition() {
            @Override
            public boolean canProceed() {
                return mActivity.getDetailsFragment().getRowsFragment().getAdapter().size() > 1;
            }

}