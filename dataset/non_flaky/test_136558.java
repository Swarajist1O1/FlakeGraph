class DummyClass_136558 {
    @Test
    public void should_generate_udt_with_custom_constructor_property_class() throws Exception {
        setExec(aptUtils -> {
            final String className = TestUDTWithCustomConstructor.class.getCanonicalName();
            final TypeElement typeElement = aptUtils.elementUtils.getTypeElement(className);

            final UDTMetaCodeGen builder = new UDTMetaCodeGen(aptUtils);

            final GlobalParsingContext globalContext = GlobalParsingContext.defaultContext();
            final EntityParsingContext context = new EntityParsingContext(typeElement,
                    ClassName.get(TestUDT.class), new LowerCaseNaming(), globalContext);
            final List<AccessorsExclusionContext> exclusionContexts = Arrays.asList(
                    new AccessorsExclusionContext("name", false, true),
                    new AccessorsExclusionContext("list", false, true));
            final List<FieldMetaSignature> fieldMetaSignatures = getTypeParsingResults(aptUtils, typeElement, exclusionContexts, globalContext);

            final List<FieldMetaSignature> constructorInjectedFieldMetaSignatures = fieldMetaSignatures
                    .stream()
                    .filter(fieldMeta -> !fieldMeta.context.fieldName.equals("date"))
                    .collect(Collectors.toList());

            final TypeSpec typeSpec = builder.buildUDTClassProperty(typeElement, context, fieldMetaSignatures, constructorInjectedFieldMetaSignatures);

            assertThat(typeSpec.toString().trim()).isEqualTo(
                    readCodeBlockFromFile("expected_code/udt_meta_builder/should_generate_udt_with_custom_constructor_property_class.txt"));

        });
        launchTest(TestUDTWithCustomConstructor.class);
    }

}