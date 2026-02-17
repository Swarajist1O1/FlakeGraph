class DummyClass_136550 {
    @Test
    public void should_fail_building_class_with_wrong_composite_partition_key_order() throws Exception {
        setExec(aptUtils -> {
            final String className = TestEntityWithWrongCompositePartitionKey.class.getCanonicalName();
            final TypeElement typeElement = aptUtils.elementUtils.getTypeElement(className);

            final EntityMetaCodeGen builder = new EntityMetaCodeGen(aptUtils);
            final List<FieldParser.FieldMetaSignature> parsingResults = getTypeParsingResults(aptUtils, typeElement, context);
            builder.buildEntityMeta(EntityType.TABLE, typeElement, context, parsingResults, emptyList());
        });
        failTestWithMessage(
                "The @PartitionKey ordering is wrong in class 'info.archinnov.achilles.internals.sample_classes.parser.entity.TestEntityWithWrongCompositePartitionKey'",
                TestEntityWithWrongCompositePartitionKey.class);
    }

}