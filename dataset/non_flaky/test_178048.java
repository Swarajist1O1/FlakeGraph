class DummyClass_178048 {
    @Test
    public void testActionWithZeroSubActions() throws Throwable {
        ExpectedSubActionResult result = setUpActionsForSubActionsTest();

        final int actionPos = 2;
        final GuidedAction selectedAction = result.actionList.get(actionPos);
        List<Integer> expectedFocusedSeq = result.expectedFocusedSeq.get(actionPos);
        List<Integer> expectedClickedSeq = result.expectedClickedSeq.get(actionPos);

        traverseSubActionsAndVerifyFocusAndClickEvents(selectedAction, actionPos, expectedFocusedSeq,
                expectedClickedSeq);
    }

}