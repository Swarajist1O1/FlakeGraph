class DummyClass_136546 {
    @Test
    public void should_fail_building_class_with_duplicated_cql_name() throws Exception {
        setExec(aptUtils -> {
            final String className = TestEntityWithDuplicateCQLColumn.class.getCanonicalName();
            final TypeElement typeElement = aptUtils.elementUtils.getTypeElement(className);

            final EntityMetaCodeGen builder = new EntityMetaCodeGen(aptUtils);
            final List<FieldParser.FieldMetaSignature> parsingResults = getTypeParsingResults(aptUtils, typeElement, context);
            builder.buildEntityMeta(EntityType.TABLE, typeElement, context, parsingResults, emptyList());
        });
        failTestWithMessage(
                "The class 'info.archinnov.achilles.internals.sample_classes.parser.entity.TestEntityWithDuplicateCQLColumn' already contains a cql column with name 'value'",
                TestEntityWithDuplicateCQLColumn.class);
    }

}