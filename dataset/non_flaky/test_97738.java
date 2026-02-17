class DummyClass_97738 {
    @Test
    public void testPrivateField() {
        final Settings settings = TestUtils.settings();
        final String output = new TypeScriptGenerator(settings).generateTypeScript(Input.from(Person2.class));
        final String expected = "\n"
                + "interface Person2 {\n"
                + "    first: string;\n"
                + "    last: string;\n"
                + "}\n"
                + "\n"
                + "interface Name {\n"
                + "    first: string;\n"
                + "    last: string;\n"
                + "}\n"
                + "";
        Assert.assertEquals(expected.trim(), output.trim());
    }

}