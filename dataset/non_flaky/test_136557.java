class DummyClass_136557 {
    @Test
    public void should_generate_udt_property_class() throws Exception {
        setExec(aptUtils -> {
            final String className = TestUDT.class.getCanonicalName();
            final TypeElement typeElement = aptUtils.elementUtils.getTypeElement(className);

            final UDTMetaCodeGen builder = new UDTMetaCodeGen(aptUtils);

            final GlobalParsingContext globalContext = GlobalParsingContext.defaultContext();
            final EntityParsingContext context = new EntityParsingContext(typeElement,
                    ClassName.get(TestUDT.class), new LowerCaseNaming(), globalContext);
            final List<FieldMetaSignature> parsingResults = getTypeParsingResults(aptUtils, typeElement, globalContext);

            final TypeSpec typeSpec = builder.buildUDTClassProperty(typeElement, context, parsingResults, Collections.emptyList());

            assertThat(typeSpec.toString().trim()).isEqualTo(
                    readCodeBlockFromFile("expected_code/udt_meta_builder/should_generate_udt_property_class.txt"));

        });
        launchTest(TestUDT.class);
    }

}