class DummyClass_178006 {
    @Test
    public void activityRecreate_notCrash() throws Throwable {
        final long dataLoadingDelay = 1000;
        Intent intent = new Intent();
        intent.putExtra(BrowseSupportFragmentTestActivity.EXTRA_LOAD_DATA_DELAY, dataLoadingDelay);
        intent.putExtra(BrowseSupportFragmentTestActivity.EXTRA_ADD_TO_BACKSTACK , false);
        intent.putExtra(BrowseSupportFragmentTestActivity.EXTRA_SET_ADAPTER_AFTER_DATA_LOAD, true);
        mActivity = activityTestRule.launchActivity(intent);

        Thread.sleep(dataLoadingDelay + TRANSITION_LENGTH);

        InstrumentationRegistry.getInstrumentation().callActivityOnRestart(mActivity);
        activityTestRule.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mActivity.recreate();
            }

}