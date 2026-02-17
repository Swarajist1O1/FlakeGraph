class DummyClass_97692 {
    @Test
    public void testTaggedUnionsDisabled() {
        final Settings settings = TestUtils.settings();
        settings.disableTaggedUnions = true;
        final String output = new TypeScriptGenerator(settings).generateTypeScript(Input.from(Geometry.class));
        final String expected = (
                "\n" +
                "interface Geometry {\n" +
                "    shapes: Shape[];\n" +
                "}\n" +
                "\n" +
                "interface Shape {\n" +
                "    kind: 'square' | 'rectangle' | 'circle';\n" +
                "}\n" +
                "\n" +
                "interface Square extends Shape {\n" +
                "    kind: 'square';\n" +
                "    size: number;\n" +
                "}\n" +
                "\n" +
                "interface Rectangle extends Shape {\n" +
                "    kind: 'rectangle';\n" +
                "    width: number;\n" +
                "    height: number;\n" +
                "}\n" +
                "\n" +
                "interface Circle extends Shape {\n" +
                "    kind: 'circle';\n" +
                "    radius: number;\n" +
                "}\n" +
                ""
                ).replace('\'', '"');
        Assert.assertEquals(expected, output);
    }

}