class DummyClass_19435 {
	@Test
	public void testBug322875_04() throws Exception {
		String testGrammarNsURI = "grammar foo.Bar with org.eclipse.xtext.common.Terminals\n " +
				" import 'http://www.eclipse.org/emf/2002/Ecore'  " +
				"Model returns EClass: name=ID;";
		String testGrammarPlatformPlugin = "grammar foo.Bar with org.eclipse.xtext.common.Terminals\n " +
				" import 'platform:/plugin/org.eclipse.emf.ecore/model/Ecore.ecore'  " +
				"Model returns EClass: name=ID;";
		XtextResource resourceOk = getResourceFromString(testGrammarNsURI);
		XtextResource resourceOk2 = (XtextResource) resourceOk.getResourceSet().createResource(URI.createURI("unused.xtext"));
		resourceOk2.load(new StringInputStream(testGrammarPlatformPlugin), null);
		Diagnostic diagOK = Diagnostician.INSTANCE.validate(resourceOk.getContents().get(0));
		assertNotNull("diag", diagOK);
		assertEquals(diagOK.toString(), 0, diagOK.getChildren().size());
		diagOK = Diagnostician.INSTANCE.validate(resourceOk2.getContents().get(0));
		assertNotNull("diag", diagOK);
		assertEquals(diagOK.toString(), 0, diagOK.getChildren().size());
	}

}