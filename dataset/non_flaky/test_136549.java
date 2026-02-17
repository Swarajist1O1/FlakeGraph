class DummyClass_136549 {
    @Test
    public void should_fail_building_class_with_wrong_computed_column() throws Exception {
        setExec(aptUtils -> {
            final String className = TestEntityWithWrongComputed.class.getCanonicalName();
            final TypeElement typeElement = aptUtils.elementUtils.getTypeElement(className);

            final EntityMetaCodeGen builder = new EntityMetaCodeGen(aptUtils);
            final List<FieldParser.FieldMetaSignature> parsingResults = getTypeParsingResults(aptUtils, typeElement, context);
            builder.buildEntityMeta(EntityType.TABLE, typeElement, context, parsingResults, emptyList());
        });
        failTestWithMessage(
                "Target field 'one' in @Computed annotation of field 'value' of class 'info.archinnov.achilles.internals.sample_classes.parser.entity.TestEntityWithWrongComputed' does not exist",
                TestEntityWithWrongComputed.class);
    }

}