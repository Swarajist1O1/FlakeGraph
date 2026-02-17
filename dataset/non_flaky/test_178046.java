class DummyClass_178046 {
    @Test
    public void testActionWithTwoSubActions() throws Throwable {
        ExpectedSubActionResult result = setUpActionsForSubActionsTest();

        final int actionPos = 0;
        final GuidedAction selectedAction = result.actionList.get(actionPos);
        List<Integer> expectedFocusedSeq = result.expectedFocusedSeq.get(actionPos);
        List<Integer> expectedClickedSeq = result.expectedClickedSeq.get(actionPos);

        traverseSubActionsAndVerifyFocusAndClickEvents(selectedAction, actionPos, expectedFocusedSeq,
                expectedClickedSeq);
    }

}