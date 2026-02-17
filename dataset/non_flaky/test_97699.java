class DummyClass_97699 {
    @Test
    public void testTaggedUnionsWithExistingProperty() {
        final Settings settings = TestUtils.settings();
        final String output = new TypeScriptGenerator(settings).generateTypeScript(Input.from(Geometry2.class));
        final String expected = (
                "\n" +
                "interface Geometry2 {\n" +
                "    shapes: Shape2Union[];\n" +
                "}\n" +
                "\n" +
                "interface Shape2 {\n" +
                "    kind: 'square' | 'rectangle' | 'circle';\n" +
                "}\n" +
                "\n" +
                "interface Square2 extends Shape2 {\n" +
                "    kind: 'square';\n" +
                "    size: number;\n" +
                "}\n" +
                "\n" +
                "interface Rectangle2 extends Shape2 {\n" +
                "    kind: 'rectangle';\n" +
                "    width: number;\n" +
                "    height: number;\n" +
                "}\n" +
                "\n" +
                "interface Circle2 extends Shape2 {\n" +
                "    kind: 'circle';\n" +
                "    radius: number;\n" +
                "}\n" +
                "\n" +
                "type Shape2Union = Square2 | Rectangle2 | Circle2;\n" +
                ""
                ).replace('\'', '"');
        Assert.assertEquals(expected, output);
    }

}