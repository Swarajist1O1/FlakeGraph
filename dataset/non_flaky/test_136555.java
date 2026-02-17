class DummyClass_136555 {
    @Test
    public void should_fail_building_view_meta_with_static_column() throws Exception {
        setExec(aptUtils -> {
            final String className = TestViewStatic.class.getCanonicalName();
            final TypeElement typeElement = aptUtils.elementUtils.getTypeElement(className);

            final EntityMetaCodeGen builder = new EntityMetaCodeGen(aptUtils);
            final List<FieldParser.FieldMetaSignature> parsingResults = getTypeParsingResults(aptUtils, typeElement, context);
            builder.buildEntityMeta(EntityType.VIEW, typeElement, context, parsingResults, emptyList());

        });
        failTestWithMessage(
                "The class 'info.archinnov.achilles.internals.sample_classes.parser.view.TestViewStatic' cannot have static columns because it is a materialized view",
                TestViewStatic.class);
    }

}