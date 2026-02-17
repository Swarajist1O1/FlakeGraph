class DummyClass_97740 {
    @Test
    public void testDuplicateProperty() throws JsonProcessingException {
        final Settings settings = TestUtils.settings();
        settings.outputKind = TypeScriptOutputKind.module;
        settings.outputFileType = TypeScriptFileType.implementationFile;
        settings.mapClasses = ClassMapping.asClasses;
        final String output = new TypeScriptGenerator(settings).generateTypeScript(Input.from(DuplicateKindUsage.class));
        Assert.assertTrue(!output.contains("DuplicateKindUnion"));
    }

}