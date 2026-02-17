class DummyClass_97695 {
    @Test
    public void testWithTypeParameter() {
        final Settings settings = TestUtils.settings();
        final String output = new TypeScriptGenerator(settings).generateTypeScript(Input.from(Earth.class));
        Assert.assertTrue(output.contains("EngineUnion"));
        Assert.assertTrue(output.contains("VehiculeUnion<M>"));
    }

}