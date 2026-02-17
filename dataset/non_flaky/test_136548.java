class DummyClass_136548 {
    @Test
    public void should_fail_building_class_with_static_without_clustering() throws Exception {
        setExec(aptUtils -> {
            final String className = TestEntityWithStaticWithoutClustering.class.getCanonicalName();
            final TypeElement typeElement = aptUtils.elementUtils.getTypeElement(className);

            final EntityMetaCodeGen builder = new EntityMetaCodeGen(aptUtils);
            final List<FieldParser.FieldMetaSignature> parsingResults = getTypeParsingResults(aptUtils, typeElement, context);
            builder.buildEntityMeta(EntityType.TABLE, typeElement, context, parsingResults, emptyList());
        });
        failTestWithMessage(
                "The class 'info.archinnov.achilles.internals.sample_classes.parser.entity.TestEntityWithStaticWithoutClustering' cannot have static columns without at least 1 clustering column",
                TestEntityWithStaticWithoutClustering.class);
    }

}