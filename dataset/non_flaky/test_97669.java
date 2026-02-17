class DummyClass_97669 {
    @Test
    public void testAllOptionalQueryParameters() {
        final Settings settings = TestUtils.settings();
        settings.outputFileType = TypeScriptFileType.implementationFile;
        settings.generateSpringApplicationClient = true;
        final String output = new TypeScriptGenerator(settings).generateTypeScript(Input.from(Controller7.class));
        Assert.assertTrue(output.contains("echo(queryParams?: { message?: string; }): RestResponse<string>"));
    }

}