class DummyClass_178047 {
    @Test
    public void testActionWithOneSubAction() throws Throwable {
        ExpectedSubActionResult result = setUpActionsForSubActionsTest();

        final int actionPos = 1;
        final GuidedAction selectedAction = result.actionList.get(actionPos);
        List<Integer> expectedFocusedSeq = result.expectedFocusedSeq.get(actionPos);
        List<Integer> expectedClickedSeq = result.expectedClickedSeq.get(actionPos);

        traverseSubActionsAndVerifyFocusAndClickEvents(selectedAction, actionPos, expectedFocusedSeq,
                expectedClickedSeq);
    }

}