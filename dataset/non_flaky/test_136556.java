class DummyClass_136556 {
    @Test
    public void should_fail_building_view_meta_without_view_annotation() throws Exception {
        setExec(aptUtils -> {
            final String className = TestEntityWithSimplePartitionKey.class.getCanonicalName();
            final TypeElement typeElement = aptUtils.elementUtils.getTypeElement(className);

            final EntityMetaCodeGen builder = new EntityMetaCodeGen(aptUtils);
            final List<FieldParser.FieldMetaSignature> parsingResults = getTypeParsingResults(aptUtils, typeElement, context);
            builder.buildEntityMeta(EntityType.VIEW, typeElement, context, parsingResults, emptyList());

        });
        failTestWithMessage(
                "Missing @MaterializedView annotation on entity class 'info.archinnov.achilles.internals.sample_classes.parser.entity.TestEntityWithSimplePartitionKey'",
                TestEntityWithSimplePartitionKey.class);
    }

}