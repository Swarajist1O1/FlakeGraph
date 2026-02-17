class DummyClass_178042 {
    @Test
    public void testFocusDisabledOnActions() throws Throwable {

        Intent intent = new Intent();
        Resources res = mContext.getResources();

        final int NUM_SEARCH_ACTIONS = 10;
        final List<Integer> ACTIONS_WITH_DISABLED_FOCUS = new ArrayList<>(
                Arrays.asList(1, 3, 4, 5, 8));
        final int ACTION_ID_SEARCH = 1;
        List<Integer> EXPECTED_ACTIONS_ID_AFTER_EACH_SELECT = new ArrayList<>();

        // we will traverse actions from top to bottom and then back to the top
        for(int i = 0; i < NUM_SEARCH_ACTIONS; i++) {
            if (!ACTIONS_WITH_DISABLED_FOCUS.contains(i))
                EXPECTED_ACTIONS_ID_AFTER_EACH_SELECT.add(i);
        }
        for(int i = EXPECTED_ACTIONS_ID_AFTER_EACH_SELECT.size(); i-- != 0;) {
            EXPECTED_ACTIONS_ID_AFTER_EACH_SELECT.add(EXPECTED_ACTIONS_ID_AFTER_EACH_SELECT.get(i));
        }


        String title = "Guided Actions Focusable Test";
        String breadcrumb = "Focusable Test Demo";
        String description = "";
        GuidanceStylist.Guidance guidance = new GuidanceStylist.Guidance(title, description,
                breadcrumb, null);

        List<GuidedAction> actionList = new ArrayList<>();
        for (int i = 0; i < NUM_SEARCH_ACTIONS; i++ ) {
            actionList.add(new GuidedAction.Builder(mContext)
                    .id(ACTION_ID_SEARCH)
                    .title(res.getString(R.string.search) + "" + i)
                    .description(res.getString(R.string.search_description) + i)
                    .build()
            );
        }
        for(int action_id : ACTIONS_WITH_DISABLED_FOCUS )
            actionList.get(action_id).setFocusable(false);

        GuidedStepAttributesTestFragment.GUIDANCE = guidance;
        GuidedStepAttributesTestFragment.ACTION_LIST = actionList;

        initActivity(intent);

        int lastSelectedActionId = -1;
        int selectIndex = 0;
        GuidedStepFragment mFragment = (GuidedStepFragment) mActivity.getGuidedStepTestFragment();
        int prevSelectedActionPosition = -1;
        int nextSelectedActionPosition = mFragment.getSelectedActionPosition();
        while ( nextSelectedActionPosition != prevSelectedActionPosition ) {
            lastSelectedActionId = mFragment.getSelectedActionPosition();
            assertTrue(res.getString(R.string.focusable_test_error_message,
                    actionList.get(lastSelectedActionId).getTitle()),
                    lastSelectedActionId == EXPECTED_ACTIONS_ID_AFTER_EACH_SELECT.get(selectIndex));
            selectIndex++;
            sendKey(KeyEvent.KEYCODE_DPAD_DOWN);
            prevSelectedActionPosition = nextSelectedActionPosition;
            nextSelectedActionPosition = mFragment.getSelectedActionPosition();
            Thread.sleep(TRANSITION_LENGTH);
        }

        prevSelectedActionPosition = -1;
        while ( nextSelectedActionPosition != prevSelectedActionPosition ) {
            lastSelectedActionId = mFragment.getSelectedActionPosition();
            assertTrue(res.getString(R.string.focusable_test_error_message,
                    actionList.get(lastSelectedActionId).getTitle()),
                    lastSelectedActionId == EXPECTED_ACTIONS_ID_AFTER_EACH_SELECT.get(selectIndex));
            selectIndex++;
            sendKey(KeyEvent.KEYCODE_DPAD_UP);
            prevSelectedActionPosition = nextSelectedActionPosition;
            nextSelectedActionPosition = mFragment.getSelectedActionPosition();
            Thread.sleep(TRANSITION_LENGTH);
        }

    }

}