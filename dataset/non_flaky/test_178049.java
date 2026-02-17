class DummyClass_178049 {
    @Test
    public void testActionWithThreeSubActions() throws Throwable {
        ExpectedSubActionResult result = setUpActionsForSubActionsTest();

        final int actionPos = 3;
        final GuidedAction selectedAction = result.actionList.get(actionPos);
        List<Integer> expectedFocusedSeq = result.expectedFocusedSeq.get(actionPos);
        List<Integer> expectedClickedSeq = result.expectedClickedSeq.get(actionPos);

        traverseSubActionsAndVerifyFocusAndClickEvents(selectedAction, actionPos, expectedFocusedSeq,
                expectedClickedSeq);
    }

}