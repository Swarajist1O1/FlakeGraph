class DummyClass_97703 {
    @Test
    public void testSorting() {
        final Settings settings = TestUtils.settings();
        settings.sortDeclarations = false;
        settings.newline = "\n";
        settings.outputFileType = TypeScriptFileType.implementationFile;
        settings.outputKind = TypeScriptOutputKind.global;
        settings.jsonLibrary = JsonLibrary.jackson2;
        settings.extensions.add(new EnumConstantsExtension());
        Assert.assertNotEquals(new TypeScriptGenerator(settings).generateTypeScript(Input.from(Emotions.class, Direction.class)),
                               new TypeScriptGenerator(settings).generateTypeScript(Input.from(Direction.class, Emotions.class)));
        settings.sortDeclarations = true;
        Assert.assertEquals(new TypeScriptGenerator(settings).generateTypeScript(Input.from(Emotions.class, Direction.class)),
                            new TypeScriptGenerator(settings).generateTypeScript(Input.from(Direction.class, Emotions.class)));
    }

}