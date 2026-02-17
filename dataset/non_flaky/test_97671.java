class DummyClass_97671 {
    @Test
    public void testReturnType() {
        final Settings settings = TestUtils.settings();
        settings.outputFileType = TypeScriptFileType.implementationFile;
        settings.generateSpringApplicationClient = true;
        final String output = new TypeScriptGenerator(settings).generateTypeScript(Input.from(Controller4.class));
        Assert.assertTrue(output.contains("getEntity(): RestResponse<Data2>"));
        Assert.assertTrue(output.contains("interface Data2"));
    }

}