class DummyClass_97674 {
    @Test
    public void testQueryParametersWithModel() {
        final Settings settings = TestUtils.settings();
        settings.outputFileType = TypeScriptFileType.implementationFile;
        settings.generateSpringApplicationClient = true;
        final String output = new TypeScriptGenerator(settings).generateTypeScript(Input.from(ControllerWithModelAttribute.class));
        Assert.assertTrue(output.contains("echoWithModelAttribute(queryParams?: { message?: string; }): RestResponse<string>"));
    }

}