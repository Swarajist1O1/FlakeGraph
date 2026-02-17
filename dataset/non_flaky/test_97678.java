class DummyClass_97678 {
    @Test
    public void testPageableController() {
        final Settings settings = TestUtils.settings();
        settings.outputFileType = TypeScriptFileType.implementationFile;
        settings.generateSpringApplicationClient = true;
        final String output = new TypeScriptGenerator(settings).generateTypeScript(Input.from(PageableController.class));
        Assert.assertTrue(output.contains(" post(queryParams?: { page?: number; size?: number; sort?: string; }): RestResponse<Page<string>>"));
    }

}