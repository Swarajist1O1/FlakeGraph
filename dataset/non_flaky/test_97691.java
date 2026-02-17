class DummyClass_97691 {
    @Test
    public void testTaggedUnionsWithOverlappingInterfaces() {
        final Settings settings = TestUtils.settings();
        final String output = new TypeScriptGenerator(settings).generateTypeScript(Input.from(IShape3.class));
        final String expected = (
                "\n" +
                "interface IShape3 {\n" +
                "    kind: 'circle' | 'rectangle';\n" +
                "}\n" +
                "\n" +
                "interface IRectangle3 extends INamedQuadrilateral3 {\n" +
                "    kind: 'rectangle';\n" +
                "}\n" +
                "\n" +
                "interface ICircle3 extends INamedShape3 {\n" +
                "    kind: 'circle';\n" +
                "}\n" +
                "\n" +
                "interface INamedQuadrilateral3 extends INamedShape3, IQuadrilateral3 {\n" +
                "    kind: 'rectangle';\n" +
                "}\n" +
                "\n" +
                "interface INamedShape3 extends IShape3 {\n" +
                "    kind: 'circle' | 'rectangle';\n" +
                "    name: string;\n" +
                "}\n" +
                "\n" +
                "interface IQuadrilateral3 extends IShape3 {\n" +
                "    kind: 'rectangle';\n" +
                "}\n" +
                "\n" +
                "type IShape3Union = IRectangle3 | ICircle3;\n"
                ).replace('\'', '"');
        Assert.assertEquals(expected, output);
    }

}