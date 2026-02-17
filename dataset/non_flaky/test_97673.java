class DummyClass_97673 {
    @Test
    public void testInheritance() {
        final Settings settings = TestUtils.settings();
        settings.outputFileType = TypeScriptFileType.implementationFile;
        settings.generateSpringApplicationClient = true;
        final String output = new TypeScriptGenerator(settings).generateTypeScript(Input.from(Controller6.class));
        Assert.assertTrue(output.contains("doSomethingElse(id: number): RestResponse<number>"));
        Assert.assertTrue(output.contains("doSomethingElseAgain(): RestResponse<number>"));
        Assert.assertTrue(output.contains("uriEncoding`test/c`"));
        Assert.assertFalse(output.contains("uriEncoding`test/b`"));
    }

}