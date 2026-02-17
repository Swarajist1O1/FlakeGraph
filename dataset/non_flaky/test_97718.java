class DummyClass_97718 {
    @Test
    public void testJAXBElement() {
        final Settings settings = TestUtils.settings();
        settings.jsonLibrary = JsonLibrary.jaxb;
        final String output = new TypeScriptGenerator(settings).generateTypeScript(Input.from(ClassWithJAXBElements.class));
        Assert.assertTrue(output.contains("ExternalReference: string"));
        Assert.assertTrue(output.contains("UserInformation: UserType"));
        Assert.assertTrue(output.contains("Source: EndPointType"));
        Assert.assertTrue(output.contains("AdditionalContextInfo: AdditionalContextType"));
    }

}