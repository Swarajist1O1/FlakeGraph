class DummyClass_97697 {
    @Test
    public void testBaseWithGenerics() {
        final Settings settings = TestUtils.settings();
        settings.outputKind = TypeScriptOutputKind.module;
        final String output = new TypeScriptGenerator(settings).generateTypeScript(Input.from(BaseUsage.class));
        Assert.assertTrue(output.contains("result: BaseUnion<string, number>"));
        Assert.assertTrue(output.contains("type BaseUnion<A, B> = FlippedGenericParameters<B, A>"));
    }

}