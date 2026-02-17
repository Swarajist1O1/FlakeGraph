class DummyClass_136543 {
    @Test
    public void should_build_view_meta() throws Exception {
        setExec(aptUtils -> {
            final String className = TestViewSensorByType.class.getCanonicalName();
            final TypeElement typeElement = aptUtils.elementUtils.getTypeElement(className);

            final EntityMetaCodeGen builder = new EntityMetaCodeGen(aptUtils);
            final List<FieldParser.FieldMetaSignature> parsingResults = getTypeParsingResults(aptUtils, typeElement, context);
            final TypeSpec typeSpec = builder.buildEntityMeta(EntityType.VIEW, typeElement, context, parsingResults, emptyList()).sourceCode;

            assertThat(buildSource(typeSpec)).isEqualTo(
                    readCodeBlockFromFile("expected_code/entity_meta_builder/should_build_view_meta.txt"));
        });
        launchTest(TestViewSensorByType.class);
    }

}