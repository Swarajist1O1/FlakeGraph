class DummyClass_97665 {
    @Test
    public void testPathParameters() {
        final Settings settings = TestUtils.settings();
        settings.outputFileType = TypeScriptFileType.implementationFile;
        settings.generateSpringApplicationClient = true;
        final String output = new TypeScriptGenerator(settings).generateTypeScript(Input.from(Controller1.class));
        Assert.assertTrue(output.contains("findPet(ownerId: number, petId: number): RestResponse<Pet>"));
        Assert.assertTrue(output.contains("uriEncoding`owners/${ownerId}/pets/${petId}`"));
        Assert.assertTrue(output.contains("interface Pet"));
    }

}