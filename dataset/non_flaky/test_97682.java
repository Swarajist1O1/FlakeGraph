class DummyClass_97682 {
    @Test
    public void testMultiValueMapRequestParam() {
        final Settings settings = TestUtils.settings();
        settings.outputFileType = TypeScriptFileType.implementationFile;
        settings.generateSpringApplicationClient = true;
        final String output = new TypeScriptGenerator(settings).generateTypeScript(Input.from(ControllerWithMultiValueMap.class));
        Assert.assertTrue(output.contains("search(queryParams?: { [index: string]: any }): RestResponse<string>"));
    }

}