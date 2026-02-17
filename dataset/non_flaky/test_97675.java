class DummyClass_97675 {
    @Test
    public void testUnwrapping() {
        final Settings settings = TestUtils.settings();
        settings.outputFileType = TypeScriptFileType.implementationFile;
        settings.generateSpringApplicationClient = true;
        settings.customTypeMappings.put("cz.habarta.typescript.generator.spring.SpringTest$Wrapper<T>", "Unwrap<T>");
        settings.importDeclarations.add("import { Unwrap } from './unwrap'");
        final String output = new TypeScriptGenerator(settings).generateTypeScript(Input.from(ControllerWithWrapper.class));
        Assert.assertTrue(output.contains("getEntity(): RestResponse<Unwrap<string>>"));
    }

}