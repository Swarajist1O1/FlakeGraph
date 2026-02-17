class DummyClass_19447 {
	@Test public void testEditGroupWithCardinality_03() throws Exception {
	public void performTest(String toBeDeleted) throws Exception {
		String grammarAsText = 
			"grammar TestLanguage with org.eclipse.xtext.common.Terminals\n" +
			"generate test 'myEcoreModel'\n" +
			"Root: value=Test;\n" +
			"Test: (" + toBeDeleted.trim() + " 'foo')*;";
		XtextResource resource = getResourceFromString(grammarAsText);
		Grammar g = (Grammar) resource.getContents().get(0);
		ParserRule rule = (ParserRule) g.getRules().get(1);
		assertEquals("*", rule.getAlternatives().getCardinality());
		resource.update(grammarAsText.indexOf(toBeDeleted), toBeDeleted.length(), "");
		// make sure we did a partial parse pass
		assertSame(rule, ((Grammar) resource.getContents().get(0)).getRules().get(1));
		assertEquals("*", rule.getAlternatives().getCardinality());
	}

}