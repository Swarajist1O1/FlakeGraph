class DummyClass_97737 {
    @Test
    public void test() {
        final Settings settings = TestUtils.settings();
        final String output = new TypeScriptGenerator(settings).generateTypeScript(Input.from(Person.class));
        final String expected = "\n"
                + "interface Person {\n"
                + "    AageA: number;\n"
                + "    AfirstA: string;\n"
                + "    AlastA: string;\n"
                + "    A_first2A: string;\n"
                + "    A_last2A: string;\n"
                + "    Aname3A: Name;\n"
                + "    BageB: number;\n"
                + "    BfirstB: string;\n"
                + "    BlastB: string;\n"
                + "    B_first2B: string;\n"
                + "    B_last2B: string;\n"
                + "    Bname3B: Name;\n"
                + "}\n"
                + "\n"
                + "interface Parent {\n"
                + "    age: number;\n"
                + "    first: string;\n"
                + "    last: string;\n"
                + "    _first2: string;\n"
                + "    _last2: string;\n"
                + "    name3: Name;\n"
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