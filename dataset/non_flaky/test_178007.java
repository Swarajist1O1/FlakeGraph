class DummyClass_178007 {
    @Test
    public void workaroundVideoViewStealFocus() {
        Intent intent = new Intent();
        mActivity = activityTestRule.launchActivity(intent);

        assertFalse(mActivity.findViewById(R.id.videoView).hasFocus());
        assertTrue(mActivity.getPlaybackFragment().getView().hasFocus());
    }

}