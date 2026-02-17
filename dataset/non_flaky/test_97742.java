class DummyClass_97742 {
    @Test
    public void testEnumAsMap() throws Exception {
//        final ObjectMapper objectMapper = new ObjectMapper();
//        final String json = objectMapper.writeValueAsString(MyEnum.MY_FIRST_VALUE);
//        System.out.println(json);

        final Settings settings = TestUtils.settings();
        settings.customTypeMappings = Collections.singletonMap("cz.habarta.typescript.generator.CustomTypeMappingTest$MyEnum", "{ code: string, definition: string }");
        final String output = new TypeScriptGenerator(settings).generateTypeScript(Input.from(MyInterfUsingEnum.class));
        assertTrue(output.contains("someValue: { code: string, definition: string }"));
    }

}