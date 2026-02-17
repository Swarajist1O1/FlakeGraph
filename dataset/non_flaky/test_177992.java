class DummyClass_177992 {
    @Test
    public void subActions() throws Throwable {
        final String firstFragmentName = generateMethodTestName("first");
        final String secondFragmentName = generateMethodTestName("second");
        final boolean[] expandSubActionInOnCreateView = new boolean[] {false};
        GuidedStepTestSupportFragment.Provider first = mockProvider(firstFragmentName);
        doAnswer(new Answer<Void>() {
            public Void answer(InvocationOnMock invocation) {
                GuidedStepTestSupportFragment.Provider obj = (GuidedStepTestSupportFragment.Provider)
                        invocation.getMock();
                if (expandSubActionInOnCreateView[0]) {
                    obj.getFragment().expandAction(obj.getFragment().findActionById(1000), false);
                }
                return null;
            }

}