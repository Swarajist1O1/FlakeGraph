class DummyClass_97727 {
    @Test
    public void jaxrsApplicationClientTest() {
        final Settings settings = TestUtils.settings();
        settings.outputFileType = TypeScriptFileType.implementationFile;
        settings.outputKind = TypeScriptOutputKind.module;
        settings.mapClasses = ClassMapping.asClasses;
        settings.extensions.add(new JsonDeserializationExtension(/*useJsonDeserializationInJaxrsApplicationClient*/true));
        settings.extensions.add(new AxiosClientExtension());
//        final File actualFile = new File("target/JaxrsWithJsonDeserialization-actual.ts");
//        new TypeScriptGenerator(settings).generateTypeScript(Input.from(JaxrsApplicationTest.OrganizationApplication.class), Output.to(actualFile));
        final String output = new TypeScriptGenerator(settings).generateTypeScript(Input.from(JaxrsApplicationTest.OrganizationApplication.class));
        Assert.assertTrue(output.contains("copyFn: Organization.fromData"));
        Assert.assertTrue(output.contains("copyFn: undefined"));
        Assert.assertTrue(output.contains("copyFn: __getCopyArrayFn(Organization.fromData)"));
    }

}