class DummyClass_178005 {
    @Test
    public void testSelectCardOnARow() throws Throwable {
        final int selectRow = 10;
        final int selectItem = 20;
        Intent intent = new Intent();
        final long dataLoadingDelay = 1000;
        intent.putExtra(BrowseSupportFragmentTestActivity.EXTRA_LOAD_DATA_DELAY, dataLoadingDelay);
        intent.putExtra(BrowseSupportFragmentTestActivity.EXTRA_ADD_TO_BACKSTACK , true);
        mActivity = activityTestRule.launchActivity(intent);

        Thread.sleep(dataLoadingDelay + TRANSITION_LENGTH);

        Presenter.ViewHolderTask itemTask = Mockito.spy(
                new ItemSelectionTask(mActivity, selectRow));

        final ListRowPresenter.SelectItemViewHolderTask task =
                new ListRowPresenter.SelectItemViewHolderTask(selectItem);
        task.setItemTask(itemTask);

        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mActivity.getBrowseTestSupportFragment().setSelectedPosition(selectRow, true, task);
            }

}