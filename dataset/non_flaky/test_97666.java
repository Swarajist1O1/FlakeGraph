class DummyClass_97666 {
    @Test
    public void testPathParametersWithoutValue() {
        final Settings settings = TestUtils.settings();
        settings.outputFileType = TypeScriptFileType.implementationFile;
        settings.generateSpringApplicationClient = true;
        final String output = new TypeScriptGenerator(settings).generateTypeScript(Input.from(Controller5.class));
        Assert.assertTrue(output.contains("findPet(ownerId: number, petId: number): RestResponse<Pet>"));
        Assert.assertTrue(output.contains("uriEncoding`owners2/${ownerId}/pets2/${petId}`"));
        Assert.assertTrue(output.contains("interface Pet"));
    }

}