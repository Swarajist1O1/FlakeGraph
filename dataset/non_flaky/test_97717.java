class DummyClass_97717 {
    @Test
    public void test() {
        final Settings settings = TestUtils.settings();
        settings.jsonLibrary = JsonLibrary.jaxb;
        final String output = new TypeScriptGenerator(settings).generateTypeScript(Input.from(MyJaxbBean.class));
        Assert.assertTrue(output.contains("king"));
        Assert.assertFalse(output.contains("age"));
    }

}