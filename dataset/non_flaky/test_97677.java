class DummyClass_97677 {
    @Test
    public void testGenericController() {
        final Settings settings = TestUtils.settings();
        settings.outputFileType = TypeScriptFileType.implementationFile;
        settings.generateSpringApplicationClient = true;
        final String output = new TypeScriptGenerator(settings).generateTypeScript(Input.from(ConcreteGenerticController.class));
        Assert.assertTrue(output.contains("post(input: string): RestResponse<number>"));
    }

}