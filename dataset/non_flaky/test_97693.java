class DummyClass_97693 {
    @Test
    public void testTaggedUnionsWithDiamond() {
        final Settings settings = TestUtils.settings();
        final String output = new TypeScriptGenerator(settings).generateTypeScript(Input.from(DiamondA.class));
        final String expected = (
                "\n" +
                "interface DiamondA {\n" +
                "    kind: 'b1' | 'c' | 'b2';\n" +
                "    a: string;\n" +
                "}\n" +
                "\n" +
                "interface DiamondB1 extends DiamondA {\n" +
                "    kind: 'b1' | 'c';\n" +
                "    b1: string;\n" +
                "}\n" +
                "\n" +
                "interface DiamondB2 extends DiamondA {\n" +
                "    kind: 'b2' | 'c';\n" +
                "    b2: string;\n" +
                "}\n" +
                "\n" +
                "interface DiamondC extends DiamondB1, DiamondB2 {\n" +
                "    kind: 'c';\n" +
                "    c: string;\n" +
                "}\n" +
                "\n" +
                "type DiamondAUnion = DiamondB1 | DiamondB2 | DiamondC;\n" +
                ""
                ).replace('\'', '"');
        Assert.assertEquals(expected, output);
    }

}