class DummyClass_97723 {
    @Test
    public void test() {
        final Settings settings = TestUtils.settings();
        settings.outputFileType = TypeScriptFileType.implementationFile;
        settings.mapClasses = ClassMapping.asClasses;
        settings.mapPackagesToNamespaces = true;
        final String output = new TypeScriptGenerator(settings).generateTypeScript(Input.from(D.class));
        final String expected = ""
                + "namespace cz.habarta.typescript.generator.p2 {\n"
                + "\n"
                + "    export class D {\n"
                + "        a: cz.habarta.typescript.generator.p1.A;\n"
                + "        b: cz.habarta.typescript.generator.p2.B;\n"
                + "        c: cz.habarta.typescript.generator.p1.C;\n"
                + "        e: cz.habarta.typescript.generator.p1.E;\n"
                + "    }\n"
                + "\n"
                + "}\n"
                + "\n"
                + "namespace cz.habarta.typescript.generator.p1 {\n"
                + "\n"
                + "    export class A {\n"
                + "        sa: string;\n"
                + "    }\n"
                + "\n"
                + "}\n"
                + "\n"
                + "namespace cz.habarta.typescript.generator.p2 {\n"
                + "\n"
                + "    export class B extends cz.habarta.typescript.generator.p1.A {\n"
                + "        sb: string;\n"
                + "    }\n"
                + "\n"
                + "}\n"
                + "\n"
                + "namespace cz.habarta.typescript.generator.p1 {\n"
                + "\n"
                + "    export class C extends cz.habarta.typescript.generator.p2.B {\n"
                + "        sc: string;\n"
                + "    }\n"
                + "\n"
                + "}\n"
                + "\n"
                + "namespace cz.habarta.typescript.generator.p1 {\n"
                + "\n"
                + "    export type E = \"Left\" | \"Right\";\n"
                + "\n"
                + "}";
        Assert.assertEquals(expected.trim(), output.trim());
    }

}