class DummyClass_97743 {
    @Test
    public void testSuperTypeString() throws Exception {
        final Settings settings = TestUtils.settings();
        settings.customTypeMappings = Collections.singletonMap("cz.habarta.typescript.generator.CustomTypeMappingTest$BaseCustomMapping", "string");
        final String output = new TypeScriptGenerator(settings).generateTypeScript(Input.from(InterfaceUsingSubCustomMapping.class));
        assertTrue(output.contains("sub: SubCustomMapping;"));
    }

}