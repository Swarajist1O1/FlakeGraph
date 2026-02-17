class DummyClass_97681 {
    @Test
    public void testUrlTrailingSlash() {
        final Settings settings = TestUtils.settings();
        settings.outputFileType = TypeScriptFileType.implementationFile;
        settings.generateSpringApplicationClient = true;
        final String output = new TypeScriptGenerator(settings).generateTypeScript(Input.from(TestUrlTrailingSlashController.class));
        Assert.assertTrue(Pattern.compile("response\\(\\):.*\\n.*uriEncoding`controller/`").matcher(output).find());
        Assert.assertTrue(Pattern.compile("response2\\(\\):.*\\n.*uriEncoding`controller`").matcher(output).find());
    }

}