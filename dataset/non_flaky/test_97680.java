class DummyClass_97680 {
    @Test
    public void testCustomControllerAnnotaion() {
        final Settings settings = TestUtils.settings();
        settings.outputFileType = TypeScriptFileType.implementationFile;
        settings.generateSpringApplicationClient = true;
        final String output = new TypeScriptGenerator(settings).generateTypeScript(Input.from(CustomAnnotatedController.class));
        Assert.assertTrue(output.contains("getText(): RestResponse<string>"));
    }

}