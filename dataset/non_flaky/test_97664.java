class DummyClass_97664 {
    @Test
    public void testApplicationScan() {
        final Settings settings = TestUtils.settings();
        settings.generateSpringApplicationInterface = true;
        settings.scanSpringApplication = true;
        settings.classLoader = Thread.currentThread().getContextClassLoader();
        final String output = new TypeScriptGenerator(settings).generateTypeScript(Input.from(SpringTestApplication.class));
        Assert.assertTrue(output.contains("interface RestApplication"));
        Assert.assertTrue(output.contains("greeting(queryParams?: { name?: string; count?: number; unnamed?: string; }): RestResponse<Greeting>"));
        Assert.assertTrue(output.contains("interface Greeting"));
    }

}