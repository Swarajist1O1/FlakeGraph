class DummyClass_97670 {
    @Test
    public void testEntityParameter() {
        final Settings settings = TestUtils.settings();
        settings.outputFileType = TypeScriptFileType.implementationFile;
        settings.generateSpringApplicationClient = true;
        final String output = new TypeScriptGenerator(settings).generateTypeScript(Input.from(Controller3.class));
        Assert.assertTrue(output.contains("setEntity(data: Data1): RestResponse<void>"));
        Assert.assertTrue(output.contains("interface Data1"));
    }

}