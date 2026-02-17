class DummyClass_177994 {
    @Test
    public void restoreFragments() throws Throwable {
        final String firstFragmentName = generateMethodTestName("first");
        final String secondFragmentName = generateMethodTestName("second");
        GuidedStepTestFragment.Provider first = mockProvider(firstFragmentName);
        doAnswer(new Answer<Void>() {
            public Void answer(InvocationOnMock invocation) {
                List actions = (List) invocation.getArguments()[0];
                actions.add(new GuidedAction.Builder().id(1000).title("OK").build());
                actions.add(new GuidedAction.Builder().id(1001).editable(true).title("text")
                        .build());
                actions.add(new GuidedAction.Builder().id(1002).editable(true).title("text")
                        .autoSaveRestoreEnabled(false).build());
                return null;
            }

}