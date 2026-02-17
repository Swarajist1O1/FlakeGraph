class DummyClass_19434 {
	@Test
	public void testBug322875_02() throws Exception {
		URIConverter.URI_MAP.put(URI.createURI("platform:/plugin/org.eclipse.emf.ecore/model/Ecore.ecore"), URI.createURI(getClass().getResource("/model/Ecore.ecore").toExternalForm()));
		String testGrammar = "grammar foo.Bar with org.eclipse.xtext.common.Terminals\n " +
				" import 'platform:/plugin/org.eclipse.emf.ecore/model/Ecore.ecore'  " +
				"Model returns EClass: name=ID;";
		XtextResource resource = getResourceFromString(testGrammar);
		Diagnostic diag = Diagnostician.INSTANCE.validate(resource.getContents().get(0));
		assertNotNull("diag", diag);
		assertEquals(diag.toString(), 0, diag.getChildren().size());
		assertEquals("diag.isOk", Diagnostic.OK, diag.getSeverity());
	}

}