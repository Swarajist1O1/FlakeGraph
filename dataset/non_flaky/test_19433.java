class DummyClass_19433 {
	@Test
	public void testBug322875_01_b() throws Exception {
		String testGrammar = "grammar foo.Bar with org.eclipse.xtext.common.Terminals\n " +
				" import 'http://www.eclipse.org/2008/Xtext' as xtext\n" +
				" import 'classpath:/org/eclipse/xtext/xtext/XtextValidationTest.ecore'  " +
				"Bug322875 returns Bug322875: referencesETypeFromClasspathPackage=[xtext::Grammar];";
		XtextResource resource = getResourceFromStringAndExpect(testGrammar,1);
		assertFalse(resource.getErrors().toString(), resource.getErrors().isEmpty());
		assertBug322875(resource);
	}

}