class DummyClass_97735 {
    @Test
    public void testAnnotatedPrivateField() {
        final Settings settings = TestUtils.settings();
        settings.optionalAnnotations.add(TypescriptOptional.class);
        final String output = new TypeScriptGenerator(settings).generateTypeScript(Input.from(SearchDTO.class));
        Assert.assertTrue(output.contains("selectedId?: number;"));
    }

}