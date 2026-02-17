class DummyClass_97720 {
    @Test
    public void testStringList() {
        final Settings settings = TestUtils.settings();
        final String output = new TypeScriptGenerator(settings).generateTypeScript(Input.from(C.class));
        Assert.assertTrue(output.contains("stringList: string[];"));
    }

}