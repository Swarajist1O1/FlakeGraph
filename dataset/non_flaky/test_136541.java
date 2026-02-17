class DummyClass_136541 {
    @Test
    public void should_build_entity_with_custom_constructor() throws Exception {
        final AptAssertOK aptAssertOK = aptUtils -> {
            final String className = TestEntityWithCustomConstructor.class.getCanonicalName();
            final TypeElement typeElement = aptUtils.elementUtils.getTypeElement(className);

            final EntityMetaCodeGen builder = new EntityMetaCodeGen(aptUtils);
            final List<AccessorsExclusionContext> exclusionContexts = Arrays.asList(
                    new AccessorsExclusionContext("id", false, true),
                    new AccessorsExclusionContext("date", false, true),
                    new AccessorsExclusionContext("value", false, true));
            final List<FieldParser.FieldMetaSignature> fieldMetaSignatures = getTypeParsingResults(aptUtils, typeElement, exclusionContexts, context);
            final TypeSpec typeSpec = builder.buildEntityMeta(EntityType.TABLE, typeElement, context, fieldMetaSignatures, fieldMetaSignatures).sourceCode;

            assertThat(buildSource(typeSpec)).isEqualTo(
                    readCodeBlockFromFile("expected_code/entity_meta_builder/should_build_entity_with_custom_constructor.txt"));
        }; setExec(aptAssertOK);
        launchTest(TestEntityWithCustomConstructor.class);
    }

}