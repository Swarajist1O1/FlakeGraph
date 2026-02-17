class DummyClass_97672 {
    @Test
    public void testGenerics() {
        final Settings settings = TestUtils.settings();
        settings.outputFileType = TypeScriptFileType.implementationFile;
        settings.generateSpringApplicationClient = true;
        final String output = new TypeScriptGenerator(settings).generateTypeScript(Input.from(Controller6.class));
        Assert.assertTrue(output.contains("doSomething(input: number[]): RestResponse<{ [P in Controller6Enum]?: any }[]>"));
        Assert.assertTrue(output.contains("type Controller6Enum"));
    }

}