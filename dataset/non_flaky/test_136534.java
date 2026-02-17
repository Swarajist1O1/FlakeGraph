class DummyClass_136534 {
    @Test
    public void should_build_entity_with_computed_column() throws Exception {
        setExec(aptUtils -> {
            final String className = TestEntityWithComputedColumn.class.getCanonicalName();
            final TypeElement typeElement = aptUtils.elementUtils.getTypeElement(className);

            final EntityMetaCodeGen builder = new EntityMetaCodeGen(aptUtils);
            final List<FieldParser.FieldMetaSignature> parsingResults = getTypeParsingResults(aptUtils, typeElement, context);
            final TypeSpec typeSpec = builder.buildEntityMeta(EntityType.TABLE, typeElement, context, parsingResults, emptyList()).sourceCode;

            assertThat(buildSource(typeSpec)).isEqualTo(
                    readCodeBlockFromFile("expected_code/entity_meta_builder/should_build_entity_with_computed_column.txt"));
        });
        launchTest(TestEntityWithComputedColumn.class);
    }

}