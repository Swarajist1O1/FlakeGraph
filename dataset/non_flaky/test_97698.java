class DummyClass_97698 {
    @Test
    public void testGenericBaseWithNonGenericSubType() {
        final Settings settings = TestUtils.settings();
        settings.outputFileType = TypeScriptFileType.implementationFile;
        settings.outputKind = TypeScriptOutputKind.module;
        settings.mapClasses = ClassMapping.asClasses;
        settings.mapEnum = EnumMapping.asEnum;
        settings.nonConstEnums = true;
        settings.mapPackagesToNamespaces = true;
        final String output = new TypeScriptGenerator(settings).generateTypeScript(Input.from(EntityCollection.class));
        Assert.assertTrue(output.contains("type EntityUnion<T> = cz.habarta.typescript.generator.TaggedUnionsTest.Foo | cz.habarta.typescript.generator.TaggedUnionsTest.Bar"));
    }

}