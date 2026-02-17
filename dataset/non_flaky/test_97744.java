class DummyClass_97744 {
    @Test
    public void testGenericClassWithCustomMapping() {
        final Settings settings = TestUtils.settings();
        settings.customTypeMappings = Collections.singletonMap("cz.habarta.typescript.generator.CustomTypeMappingTest$GenericClass<D>", "CustomGenericClass<D>");
        final String output = new TypeScriptGenerator(settings).generateTypeScript(Input.from(GenericClass.class));
        Assert.assertEquals("", output);
    }

}