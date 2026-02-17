class DummyClass_97668 {
    @Test
    public void testQueryParameters() {
        final Settings settings = TestUtils.settings();
        settings.outputFileType = TypeScriptFileType.implementationFile;
        settings.generateSpringApplicationClient = true;
        final String output = new TypeScriptGenerator(settings).generateTypeScript(Input.from(Controller2.class));
        Assert.assertTrue(output.contains("echo(queryParams: { message: string; count?: number; optionalRequestParam?: number; }): RestResponse<string>"));
    }

}