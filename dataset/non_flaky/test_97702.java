class DummyClass_97702 {
    @Test
    public void testInTypeScriptGenerator() {
        final Settings settings = new Settings();
        settings.newline = "\n";
        settings.outputFileType = TypeScriptFileType.implementationFile;
        settings.outputKind = TypeScriptOutputKind.global;
        settings.jsonLibrary = JsonLibrary.jackson2;
        settings.extensions.add(new EnumConstantsExtension());
        final String actual = new TypeScriptGenerator(settings).generateTypeScript(Input.from(Direction.class));
        Assert.assertTrue(actual.contains("const Direction"));
        Assert.assertTrue(actual.contains("North"));
    }

}