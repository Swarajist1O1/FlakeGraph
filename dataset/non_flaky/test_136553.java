class DummyClass_136553 {
    @Test
    public void should_fail_building_view_meta_with_entity_annotation() throws Exception {
        setExec(aptUtils -> {
            final String className = TestViewWithEntityAnnotation.class.getCanonicalName();
            final TypeElement typeElement = aptUtils.elementUtils.getTypeElement(className);

            final EntityMetaCodeGen builder = new EntityMetaCodeGen(aptUtils);
            final List<FieldParser.FieldMetaSignature> parsingResults = getTypeParsingResults(aptUtils, typeElement, context);
            builder.buildEntityMeta(EntityType.VIEW, typeElement, context, parsingResults, emptyList());

        });
        failTestWithMessage(
                "Cannot have both @Table and @MaterializedView on the class 'info.archinnov.achilles.internals.sample_classes.parser.view.TestViewWithEntityAnnotation'",
                TestViewWithEntityAnnotation.class);
    }

}