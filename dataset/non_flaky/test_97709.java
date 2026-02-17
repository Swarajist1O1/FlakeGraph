class DummyClass_97709 {
    @Test
    public void testNonGeneric() {
        final Settings settings = TestUtils.settings();
        settings.customTypeAliases = Collections.singletonMap("Id", "string");
        final String output = new TypeScriptGenerator(settings).generateTypeScript(Input.from());
        Assert.assertTrue(output.contains("type Id = string"));
    }

}