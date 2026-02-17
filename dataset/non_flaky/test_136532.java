class DummyClass_136532 {
    @Test
    public void should_build_entity_with_static_counter_column() throws Exception {
        setExec(aptUtils -> {
            final String className = TestEntityWithStaticCounterColumn.class.getCanonicalName();
            final TypeElement typeElement = aptUtils.elementUtils.getTypeElement(className);

            final EntityMetaCodeGen builder = new EntityMetaCodeGen(aptUtils);
            final List<FieldParser.FieldMetaSignature> parsingResults = getTypeParsingResults(aptUtils, typeElement, context);
            final TypeSpec typeSpec = builder.buildEntityMeta(EntityType.TABLE, typeElement, context, parsingResults, emptyList()).sourceCode;

            assertThat(buildSource(typeSpec)).isEqualTo(
                    readCodeBlockFromFile("expected_code/entity_meta_builder/should_build_entity_with_static_counter_column.txt"));
        });
        launchTest(TestEntityWithStaticCounterColumn.class);
    }

}