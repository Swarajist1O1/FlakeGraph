class DummyClass_97690 {
    @Test
    public void testTaggedUnionsWithInterfaces() {
        final Settings settings = TestUtils.settings();
        final String output = new TypeScriptGenerator(settings).generateTypeScript(Input.from(IShape2.class));
        final String expected = (
                "\n" +
                "interface IShape2 {\n" +
                "    kind: 'circle' | 'square' | 'rectangle';\n" +
                "}\n" +
                "\n" +
                "interface CSquare2 extends IQuadrilateral2 {\n" +
                "    kind: 'square';\n" +
                "    size: number;\n" +
                "}\n" +
                "\n" +
                "interface CRectangle2 extends IQuadrilateral2 {\n" +
                "    kind: 'rectangle';\n" +
                "    width: number;\n" +
                "    height: number;\n" +
                "}\n" +
                "\n" +
                "interface CCircle2 extends IShape2 {\n" +
                "    kind: 'circle';\n" +
                "    radius: number;\n" +
                "}\n" +
                "\n" +
                "interface IQuadrilateral2 extends IShape2 {\n" +
                "    kind: 'square' | 'rectangle';\n" +
                "}\n" +
                "\n" +
                "type IShape2Union = CSquare2 | CRectangle2 | CCircle2;\n" +
                ""
                ).replace('\'', '"');
        Assert.assertEquals(expected, output);
    }

}