class DummyClass_136529 {
    @Test
    public void should_build_entity_with_composite_partition_key() throws Exception {
        setExec(aptUtils -> {
            final String className = TestEntityWithCompositePartitionKey.class.getCanonicalName();
            final TypeElement typeElement = aptUtils.elementUtils.getTypeElement(className);

            final EntityMetaCodeGen builder = new EntityMetaCodeGen(aptUtils);
            final List<FieldParser.FieldMetaSignature> parsingResults = getTypeParsingResults(aptUtils, typeElement, context);
            final TypeSpec typeSpec = builder.buildEntityMeta(EntityType.TABLE, typeElement, context, parsingResults, emptyList()).sourceCode;

            assertThat(buildSource(typeSpec)).isEqualTo(
                    readCodeBlockFromFile("expected_code/entity_meta_builder/should_build_entity_with_composite_partition_key.txt"));
        });
        launchTest(TestEntityWithCompositePartitionKey.class);
    }

}