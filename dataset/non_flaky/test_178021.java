class DummyClass_178021 {
    @Test
    public void immediateRemoveFragment() throws Throwable {
        Intent intent = new Intent();
        ActivityTestRule<ImmediateRemoveFragmentActivity> activityTestRule =
                new ActivityTestRule<>(ImmediateRemoveFragmentActivity.class, false, false);
        ImmediateRemoveFragmentActivity activity = activityTestRule.launchActivity(intent);

        Thread.sleep(1000);
    }

}