class DummyClass_136547 {
    @Test
    public void should_fail_building_class_with_no_partition_key() throws Exception {
        setExec(aptUtils -> {
            final String className = TestEntityWithNoPartitionKey.class.getCanonicalName();
            final TypeElement typeElement = aptUtils.elementUtils.getTypeElement(className);

            final EntityMetaCodeGen builder = new EntityMetaCodeGen(aptUtils);
            final List<FieldParser.FieldMetaSignature> parsingResults = getTypeParsingResults(aptUtils, typeElement, context);
            builder.buildEntityMeta(EntityType.TABLE, typeElement, context, parsingResults, emptyList());
        });
        failTestWithMessage(
                "The class 'info.archinnov.achilles.internals.sample_classes.parser.entity.TestEntityWithNoPartitionKey' should have at least 1 partition key (@PartitionKey)",
                TestEntityWithNoPartitionKey.class);
    }

}