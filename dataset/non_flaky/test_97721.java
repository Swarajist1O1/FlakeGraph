class DummyClass_97721 {
    @Test
    public void testStringKeyMapNumberValue() {
        final Settings settings = TestUtils.settings();
        final String output = new TypeScriptGenerator(settings).generateTypeScript(Input.from(D.class));
        Assert.assertTrue(output.contains("stringKeyMap: { [index: string]: number };"));
    }

}