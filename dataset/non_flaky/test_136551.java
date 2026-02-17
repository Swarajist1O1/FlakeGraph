class DummyClass_136551 {
    @Test
    public void should_fail_building_class_with_wrong_clustering_column_order() throws Exception {
        setExec(aptUtils -> {
            final String className = TestEntityWithWrongClusteringColumns.class.getCanonicalName();
            final TypeElement typeElement = aptUtils.elementUtils.getTypeElement(className);

            final EntityMetaCodeGen builder = new EntityMetaCodeGen(aptUtils);
            final List<FieldParser.FieldMetaSignature> parsingResults = getTypeParsingResults(aptUtils, typeElement, context);
            builder.buildEntityMeta(EntityType.TABLE, typeElement, context, parsingResults, emptyList());
        });
        failTestWithMessage(
                "The @ClusteringColumn ordering is wrong in class 'info.archinnov.achilles.internals.sample_classes.parser.entity.TestEntityWithWrongClusteringColumns'",
                TestEntityWithWrongClusteringColumns.class);
    }

}