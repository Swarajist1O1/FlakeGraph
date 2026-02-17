class DummyClass_19448 {
	@Test public void testBug285605() throws Exception {
	public void acceptWarning(String message, EObject object, EStructuralFeature feature, int index, String code,
			String... issueData) {
		if (code.equals(OverriddenValueInspector.ISSUE_CODE)) {
			String expectation = "The assigned value of feature 'feature' will possibly override itself because it is used inside of a loop.";
			assertEquals(expectation, message);
		} else {
			super.acceptWarning(message, object, feature, index, code, issueData);
		}
	}

}