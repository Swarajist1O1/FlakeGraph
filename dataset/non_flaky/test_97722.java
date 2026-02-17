class DummyClass_97722 {
    @Test
    public void testStringKeyMapGenericValue() {
        final Settings settings = TestUtils.settings();
        final String output = new TypeScriptGenerator(settings).generateTypeScript(Input.from(E.class));
        Assert.assertTrue(output.contains("stringKeyMap: { [index: string]: T };"));
    }

}