class DummyClass_97685 {
    @Test
    public void test() {
        final Settings settings = TestUtils.settings();
        settings.generateReadonlyAndWriteonlyJSDocTags = true;
        final String output = new TypeScriptGenerator(settings).generateTypeScript(Input.from(ReadOnlyWriteOnlyUser.class));
        final String expected = "\n"
                + "interface ReadOnlyWriteOnlyUser {\n"
                + "    name: string;\n"
                + "    /**\n"
                + "     * @readonly\n"
                + "     */\n"
                + "    id1: string;\n"
                + "    /**\n"
                + "     * @writeonly\n"
                + "     */\n"
                + "    password1: string;\n"
                + "    /**\n"
                + "     * @readonly\n"
                + "     */\n"
                + "    id2: string;\n"
                + "    /**\n"
                + "     * @writeonly\n"
                + "     */\n"
                + "    password2: string;\n"
                + "}\n";
        Assert.assertEquals(expected, output);
    }

}