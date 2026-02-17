class DummyClass_178044 {
    @Test
    public void testToggleEnabledFlags() throws Throwable {

        Intent intent = new Intent();
        Resources res = mContext.getResources();

        final int NUM_SEARCH_ACTIONS = 10;
        final List<Integer> DISABLED_ACTIONS = new ArrayList<>(
                Arrays.asList(1, 3, 5, 7));
        final int ACTION_ID_REVERT_BUTTON = 0;
        final int ACTION_ID_SEARCH_BEGIN = ACTION_ID_REVERT_BUTTON + 1;
        int ACTION_ID_SEARCH_END = ACTION_ID_SEARCH_BEGIN;

        // sequence of clicked actions simulated in the test
        List<Integer> CLICK_SEQUENCE = new ArrayList<>();

        // Expected Clicked sequence can be different from focused ones since some of the actions
        // are disabled hence not clickable
        List<Integer> EXPECTED_FOCUSED_SEQUENCE = new ArrayList<>();
        List<Integer> EXPECTED_CLICKED_SEQUENCE = new ArrayList<>();
        // Expected actions state according to list of DISABLED_ACTIONS: false for disabled actions
        List<Boolean> EXPECTED_ACTIONS_STATE = new ArrayList<>(
                Arrays.asList(new Boolean[NUM_SEARCH_ACTIONS])
        );
        Collections.fill(EXPECTED_ACTIONS_STATE, Boolean.FALSE);

        for(int i = 0; i < NUM_SEARCH_ACTIONS; i++) {
            CLICK_SEQUENCE.add(i + 1);
        }
        for(int clickedActionId : CLICK_SEQUENCE) {
            EXPECTED_FOCUSED_SEQUENCE.add(clickedActionId);
            if (DISABLED_ACTIONS.contains(clickedActionId - 1))
                EXPECTED_CLICKED_SEQUENCE.add(clickedActionId);
            else
                EXPECTED_CLICKED_SEQUENCE.add(-1);
        }

        String title = "Guided Actions Enabled Test";
        String breadcrumb = "Toggle Enabled Flag Test Demo";
        String description = "";
        GuidanceStylist.Guidance guidance = new GuidanceStylist.Guidance(title, description,
                breadcrumb, null);

        List<GuidedAction> actionList = new ArrayList<>();
        actionList.add(new GuidedAction.Builder(mContext)
                .id(ACTION_ID_REVERT_BUTTON)
                .title(res.getString(R.string.invert_title))
                .description(res.getString(R.string.revert_description))
                .build()
        );

        for (int i = 0; i < NUM_SEARCH_ACTIONS; i++ ) {
            actionList.add(new GuidedAction.Builder(mContext)
                    .id(ACTION_ID_SEARCH_END++)
                    .title(res.getString(R.string.search) + "" + i)
                    .description(res.getString(R.string.search_description) + i)
                    .build()
            );
        }
        for(int action_id : DISABLED_ACTIONS ) {
            if ( action_id >= 0 && action_id < NUM_SEARCH_ACTIONS ) {
                actionList.get(action_id + 1).setEnabled(false);
                EXPECTED_ACTIONS_STATE.set(action_id, Boolean.TRUE);
            }
        }

        GuidedStepAttributesTestFragment.clear();
        GuidedStepAttributesTestFragment.GUIDANCE = guidance;
        GuidedStepAttributesTestFragment.ACTION_LIST = actionList;
        GuidedStepAttributesTestFragment.setActionClickCallback(ACTION_ID_REVERT_BUTTON,
                sRevertCallback);

        initActivity(intent);

        final GuidedStepFragment mFragment = (GuidedStepFragment)
                mActivity.getGuidedStepTestFragment();

        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mFragment.setSelectedActionPosition(0);
            }

}