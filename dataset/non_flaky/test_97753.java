class DummyClass_97753 {
    @Test
    public void testIntermediateInterfacesWithoutTypeParams() throws Exception {
        final Settings settings = TestUtils.settings();

        final Jackson2Parser jacksonParser = new Jackson2Parser(settings, new DefaultTypeProcessor());
        final Model model = jacksonParser.parseModel(Implementation.class);
        final ModelCompiler modelCompiler = new TypeScriptGenerator(settings).getModelCompiler();

        final TsModel result = modelCompiler.javaToTypeScript(model);

        MatcherAssert.assertThat(
                result.getBean(WithoutTypeParam.class).getProperties().get(0).tsType,
                CoreMatchers.instanceOf(TsType.UnionType.class)
        );
    }

}