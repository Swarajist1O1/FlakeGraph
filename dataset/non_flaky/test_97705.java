class DummyClass_97705 {
    @Test
    public void testExclude() {
        final Settings settings = TestUtils.settings();
        settings.jsonLibrary = library;
        settings.excludePropertyAnnotations = Arrays.asList(MyExclude.class);
        final String output = new TypeScriptGenerator(settings).generateTypeScript(Input.from(ClassWithAnnotatedProperties.class));
        Assert.assertTrue(output.contains("property1"));
        Assert.assertTrue(output.contains("property2"));
        Assert.assertTrue(!output.contains("property3"));
        Assert.assertTrue(!output.contains("property4"));
    }

}