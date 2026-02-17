class DummyClass_97676 {
    @Test
    public void testUnwrappingNew() {
        final Settings settings = TestUtils.settings();
        settings.outputFileType = TypeScriptFileType.implementationFile;
        settings.generateSpringApplicationClient = true;
        settings.customTypeMappings.put("cz.habarta.typescript.generator.spring.SpringTest$Wrapper<T>", "T");
        final String output = new TypeScriptGenerator(settings).generateTypeScript(Input.from(ControllerWithWrapper.class));
        Assert.assertTrue(output.contains("getEntity(): RestResponse<string>"));
    }

}