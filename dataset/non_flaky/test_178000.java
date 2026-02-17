class DummyClass_178000 {
    @Test
    public void setSurfaceViewCallbackBeforeCreate() {
        Intent intent = new Intent();
        mActivity = activityTestRule.launchActivity(intent);

        InstrumentationRegistry.getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                mActivity.replaceVideoFragment();
            }

}