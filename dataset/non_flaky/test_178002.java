class DummyClass_178002 {
    @Test
    public void testTwoBackKeysWithBackStack() throws Throwable {
        final long dataLoadingDelay = 1000;
        Intent intent = new Intent();
        intent.putExtra(BrowseSupportFragmentTestActivity.EXTRA_LOAD_DATA_DELAY, dataLoadingDelay);
        intent.putExtra(BrowseSupportFragmentTestActivity.EXTRA_ADD_TO_BACKSTACK , true);
        mActivity = activityTestRule.launchActivity(intent);

        Thread.sleep(dataLoadingDelay + TRANSITION_LENGTH);

        assertNotNull(mActivity.getBrowseTestSupportFragment().getMainFragment());
        sendKeys(KeyEvent.KEYCODE_DPAD_RIGHT);
        Thread.sleep(TRANSITION_LENGTH);
        sendKeys(KeyEvent.KEYCODE_BACK, KeyEvent.KEYCODE_BACK);
    }

}