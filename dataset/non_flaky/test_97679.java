class DummyClass_97679 {
    @Test
    public void testDoubleGenericController() {
        final Settings settings = TestUtils.settings();
        settings.outputFileType = TypeScriptFileType.implementationFile;
        settings.generateSpringApplicationClient = true;
        final String output = new TypeScriptGenerator(settings).generateTypeScript(Input.from(DoubleGenericController.class));
        Assert.assertTrue(output.contains(" get(): RestResponse<string[]>"));
    }

}