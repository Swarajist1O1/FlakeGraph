class DummyClass_97719 {
    @Test
    public void testOrder1() {
        final Settings settings = TestUtils.settings();
        settings.sortDeclarations = true;
        String expectedA = "" +
                "\n" +
                "interface A {\n" +
                "    mapExt: { [index: string]: any };\n" +
                "}\n";
        String expectedB = "" +
                "\n" +
                "interface B {\n" +
                "    mapExt: { [index: string]: number };\n" +
                "}\n";
        final String actualA = new TypeScriptGenerator(settings).generateTypeScript(Input.from(A.class));
        final String actualB = new TypeScriptGenerator(settings).generateTypeScript(Input.from(B.class));

        assertEquals(expectedA, actualA);
        assertEquals(expectedB, actualB);
    }

}