class DummyClass_177996 {
    @Test
    public void finishGuidedStepFragment_finishes_fragments() throws Throwable {
        final String firstFragmentName = generateMethodTestName("first");
        GuidedStepTestFragment.Provider first = mockProvider(firstFragmentName);
        doAnswer(new Answer<Void>() {
            public Void answer(InvocationOnMock invocation) {
                List actions = (List) invocation.getArguments()[0];
                actions.add(new GuidedAction.Builder().id(1001).title("Finish fragments").build());
                return null;
            }

}