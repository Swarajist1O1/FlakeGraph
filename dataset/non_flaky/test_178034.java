class DummyClass_178034 {
    @Test
    public void testDecemberToNovemberTransition() throws Throwable {
        long startTime = System.currentTimeMillis();
        Intent intent = new Intent();

        String title = "Date Picker Transition Test";
        String breadcrumb = "Month Transition Test Demo";
        String description = "Testing the transition from Dec to Nov";
        GuidanceStylist.Guidance guidance = new GuidanceStylist.Guidance(title, description,
                breadcrumb, null);

        List<GuidedAction> actionList = new ArrayList<>();

        Calendar cal = Calendar.getInstance();

        cal.set(Calendar.YEAR, 2016);
        cal.set(Calendar.MONTH, Calendar.DECEMBER);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date initialDate = cal.getTime();

        GuidedDatePickerAction action = new GuidedDatePickerAction.Builder(
                mContext)
                .id(0)
                .title("Date")
                .date(initialDate.getTime())
                .datePickerFormat("DMY")
                .build();

        actionList.add(action);

        GuidedStepAttributesTestFragment.clear();
        GuidedStepAttributesTestFragment.GUIDANCE = guidance;
        GuidedStepAttributesTestFragment.ACTION_LIST = actionList;

        initActivity(intent);

        DatePicker mPickerView = (DatePicker) mActivity.findViewById(
                R.id.guidedactions_activator_item);

        verticalScrollToFieldValue(Calendar.MONTH, Calendar.NOVEMBER, new int[] {0, 1, 2},
                mPickerView, KeyEvent.KEYCODE_DPAD_UP);
        long executionTime = System.currentTimeMillis() - startTime;
        Log.d(TAG, "testDecemberToNovember() Execution time: " + executionTime);
        Thread.sleep(FINAL_WAIT);
    }

}