class DummyClass_178045 {
    @Test
    public void testCheckedActions() throws Throwable {

        Intent intent = new Intent();
        Resources res = mContext.getResources();

        final int NUM_RADIO_ACTIONS = 3;
        final int NUM_CHECK_BOX_ACTIONS = 3;
        final int INITIALLY_CHECKED_RADIO_ACTION = 0;
        final List<Integer> INITIALLY_CHECKED_CHECKBOX_ACTIONS = new ArrayList<>(
                Arrays.asList(1, 2)
        );

        List<Integer> CLICK_SEQUENCE = new ArrayList<>();
        for(int i = 0; i < NUM_RADIO_ACTIONS + NUM_CHECK_BOX_ACTIONS; i++) {
            CLICK_SEQUENCE.add(i);
        }

        List<Boolean> EXPECTED_ACTIONS_STATE_AFTER_EACH_CLICK = new ArrayList<>(
                Arrays.asList(new Boolean[CLICK_SEQUENCE.size()])
        );
        Collections.fill(EXPECTED_ACTIONS_STATE_AFTER_EACH_CLICK, Boolean.FALSE);

        // initial state of actions before any clicks happen
        EXPECTED_ACTIONS_STATE_AFTER_EACH_CLICK.set(INITIALLY_CHECKED_RADIO_ACTION, true);
        for(int checkedCheckBox : INITIALLY_CHECKED_CHECKBOX_ACTIONS) {
            EXPECTED_ACTIONS_STATE_AFTER_EACH_CLICK.set(NUM_RADIO_ACTIONS + checkedCheckBox, true);
        }

        String title = "Guided Actions Checked Test";
        String breadcrumb = "Checked Test Demo";
        String description = "";
        GuidanceStylist.Guidance guidance = new GuidanceStylist.Guidance(title, description,
                breadcrumb, null);

        List<GuidedAction> actionList = new ArrayList<>();
        actionList.add(new GuidedAction.Builder(mContext)
                .title(res.getString(R.string.radio_actions_info_title))
                .description(res.getString(R.string.radio_actions_info_desc))
                .infoOnly(true)
                .enabled(true)
                .focusable(false)
                .build()
        );

        int firstRadioActionIndex = actionList.size();
        for(int i = 0; i < NUM_RADIO_ACTIONS; i++) {
            actionList.add(new GuidedAction.Builder(mContext)
                    .title(res.getString(R.string.checkbox_title) + i)
                    .description(res.getString(R.string.checkbox_desc) + i)
                    .checkSetId(GuidedAction.DEFAULT_CHECK_SET_ID)
                    .build()
            );
            if (i == INITIALLY_CHECKED_RADIO_ACTION)
                actionList.get(firstRadioActionIndex + i).setChecked(true);
        }

        actionList.add(new GuidedAction.Builder(mContext)
                .title(res.getString(R.string.checkbox_actions_info_title))
                .description(res.getString(R.string.checkbox_actions_info_desc))
                .infoOnly(true)
                .enabled(true)
                .focusable(false)
                .build()
        );
        int firstCheckBoxActionIndex = actionList.size();
        for(int i = 0; i < NUM_CHECK_BOX_ACTIONS; i++) {
            actionList.add(new GuidedAction.Builder(mContext)
                    .title(res.getString(R.string.checkbox_title) + i)
                    .description(res.getString(R.string.checkbox_desc) + i)
                    .checkSetId(GuidedAction.CHECKBOX_CHECK_SET_ID)
                    .build()
            );
        }
        for(int i = 0; i < INITIALLY_CHECKED_CHECKBOX_ACTIONS.size(); i++ ) {
            actionList.get(firstCheckBoxActionIndex + INITIALLY_CHECKED_CHECKBOX_ACTIONS.get(i))
                    .setChecked(true);
        }

        GuidedStepAttributesTestFragment.GUIDANCE = guidance;
        GuidedStepAttributesTestFragment.ACTION_LIST = actionList;
        initActivity(intent);

        examineCheckedAndUncheckedActions(actionList, EXPECTED_ACTIONS_STATE_AFTER_EACH_CLICK,
                NUM_RADIO_ACTIONS, NUM_CHECK_BOX_ACTIONS);
    }

}