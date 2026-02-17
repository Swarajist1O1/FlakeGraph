class DummyClass_97736 {
    @Test
    public void testOptionalAndRequiredProperty() {
        {
            final Settings settings = TestUtils.settings();
            settings.optionalAnnotations = Arrays.asList();
            settings.requiredAnnotations = Arrays.asList();
            final String output = new TypeScriptGenerator(settings).generateTypeScript(Input.from(ClassWithMarkedField.class));
            Assert.assertTrue(output.contains("a: string;"));
            Assert.assertTrue(output.contains("b: string;"));
        }
        {
            final Settings settings = TestUtils.settings();
            settings.optionalAnnotations = Arrays.asList(MarkerAnnotation.class);
            settings.requiredAnnotations = Arrays.asList();
            final String output = new TypeScriptGenerator(settings).generateTypeScript(Input.from(ClassWithMarkedField.class));
            Assert.assertTrue(output.contains("a: string;"));
            Assert.assertTrue(output.contains("b?: string;"));
        }
        {
            final Settings settings = TestUtils.settings();
            settings.optionalAnnotations = Arrays.asList();
            settings.requiredAnnotations = Arrays.asList(MarkerAnnotation.class);
            final String output = new TypeScriptGenerator(settings).generateTypeScript(Input.from(ClassWithMarkedField.class));
            Assert.assertTrue(output.contains("a?: string;"));
            Assert.assertTrue(output.contains("b: string;"));
        }
        try {
            final Settings settings = TestUtils.settings();
            settings.optionalAnnotations = Arrays.asList(MarkerAnnotation.class);
            settings.requiredAnnotations = Arrays.asList(MarkerAnnotation.class);
            new TypeScriptGenerator(settings).generateTypeScript(Input.from(ClassWithMarkedField.class));
            Assert.fail();
        } catch (Exception e) {
            // expected - optionalAnnotations and requiredAnnotations cannot be used together
        }
    }

}