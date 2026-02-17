class DummyClass_178001 {
    @Test
    public void setSurfaceViewCallbackAfterCreate() {
        Intent intent = new Intent();
        mActivity = activityTestRule.launchActivity(intent);

        VideoFragment fragment = (VideoFragment) mActivity.getFragmentManager().findFragmentById(
                R.id.video_fragment);
        assertNotNull(fragment);

        fragment.setSurfaceHolderCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
            }

}