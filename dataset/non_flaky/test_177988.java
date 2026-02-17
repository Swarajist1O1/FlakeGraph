class DummyClass_177988 {
    @Test
    public void nextAndBack() throws Throwable {
        final String firstFragmentName = generateMethodTestName("first");
        final String secondFragmentName = generateMethodTestName("second");
        GuidedStepTestSupportFragment.Provider first = mockProvider(firstFragmentName);
        doAnswer(new Answer<Void>() {
            public Void answer(InvocationOnMock invocation) {
                List actions = (List) invocation.getArguments()[0];
                actions.add(new GuidedAction.Builder().id(1000).title("OK").build());
                return null;
            }

}