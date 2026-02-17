class DummyClass_97667 {
    @Test
    public void testPathParameterWithReservedWord() {
        final Settings settings = TestUtils.settings();
        settings.outputFileType = TypeScriptFileType.implementationFile;
        settings.generateSpringApplicationClient = true;
        final String output = new TypeScriptGenerator(settings).generateTypeScript(Input.from(ControllerWithReservedWord.class));
        Assert.assertTrue(output.contains("getLogs(_class: string): RestResponse<string[]>"));
        Assert.assertTrue(output.contains("uriEncoding`logs/${_class}`"));
    }

}