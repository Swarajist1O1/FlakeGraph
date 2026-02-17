class DummyClass_19436 {
	@Test
	public void testBug_280413_03() throws Exception {
		XtextResource resource = getResourceFromString(
				"grammar org.foo.Bar with org.eclipse.xtext.common.Terminals\n" +
				"import 'classpath:/org/eclipse/xtext/Xtext.ecore' as xtext\n" +
				"ParserRule returns xtext::ParserRule: name = ID;");
		assertTrue(resource.getErrors().toString(), resource.getErrors().isEmpty());
		assertTrue(resource.getWarnings().toString(), resource.getWarnings().isEmpty());

		Diagnostic diag = Diagnostician.INSTANCE.validate(resource.getContents().get(0));
		assertNotNull("diag", diag);
		assertEquals(diag.getSeverity(), Diagnostic.OK);
		assertTrue(diag.getChildren().toString(), diag.getChildren().isEmpty());
	}

}