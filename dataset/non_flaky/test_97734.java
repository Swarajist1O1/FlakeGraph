class DummyClass_97734 {
    @Test
    public void testNullableTypeAnnotation() {
        Settings settings = TestUtils.settings();
        settings.optionalAnnotations.add(NullableType.class);
        final String output = new TypeScriptGenerator(settings).generateTypeScript(Input.from(BeanWithNullableType.class));
        Assert.assertTrue(output.contains("property1?: string;"));
        Assert.assertTrue(output.contains("property2?: string;"));
    }

}