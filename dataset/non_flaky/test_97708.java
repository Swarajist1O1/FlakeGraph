class DummyClass_97708 {
    @Test
    public void testGeneric() {
        final Settings settings = TestUtils.settings();
        settings.outputKind = TypeScriptOutputKind.module;
        settings.customTypeAliases = Collections.singletonMap("Id<T>", "string");
        settings.customTypeMappings = Collections.singletonMap("cz.habarta.typescript.generator.CustomTypeAliasesTest$IdRepresentation<T>", "Id<T>");
        final String output = new TypeScriptGenerator(settings).generateTypeScript(Input.from(MyEntityRepresentation.class));
        Assert.assertTrue(output.contains("id: Id<MyEntityRepresentation>"));
        Assert.assertTrue(output.contains("export type Id<T> = string"));
    }

}