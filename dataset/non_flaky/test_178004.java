class DummyClass_178004 {
    @Test
    public void testPressRightBeforeMainFragmentCreated() throws Throwable {
        final long dataLoadingDelay = 1000;
        Intent intent = new Intent();
        intent.putExtra(BrowseSupportFragmentTestActivity.EXTRA_LOAD_DATA_DELAY, dataLoadingDelay);
        intent.putExtra(BrowseSupportFragmentTestActivity.EXTRA_ADD_TO_BACKSTACK , false);
        mActivity = activityTestRule.launchActivity(intent);

        assertNull(mActivity.getBrowseTestSupportFragment().getMainFragment());
        sendKeys(KeyEvent.KEYCODE_DPAD_RIGHT);
    }

}