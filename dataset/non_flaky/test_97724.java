class DummyClass_97724 {
    @Test
    public void testNested() {
        final Settings settings = TestUtils.settings();
        settings.outputFileType = TypeScriptFileType.implementationFile;
        settings.mapClasses = ClassMapping.asClasses;
        settings.mapPackagesToNamespaces = true;
        settings.sortTypeDeclarations = true;
        final String output = new TypeScriptGenerator(settings).generateTypeScript(Input.from(Outer.Inner.class, Outer.class));
        final String expected = ""
                + "namespace cz.habarta.typescript.generator.FullyQualifiedNamesTest {\n"
                + "\n"
                + "    export class Outer {\n"
                + "        outer: string;\n"
                + "    }\n"
                + "\n"
                + "}\n"
                + "\n"
                + "namespace cz.habarta.typescript.generator.FullyQualifiedNamesTest.Outer {\n"
                + "\n"
                + "    export class Inner {\n"
                + "        inner: string;\n"
                + "    }\n"
                + "\n"
                + "}\n";
        Assert.assertEquals(expected.trim(), output.trim());
    }

}