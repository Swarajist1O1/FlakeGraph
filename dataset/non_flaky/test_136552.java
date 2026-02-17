class DummyClass_136552 {
    @Test
    public void should_fail_building_child_class_with_shadowed_field() throws Exception {
        setExec(aptUtils -> {
            final String className = TestEntityAsChildShadowingVariable.class.getCanonicalName();
            final TypeElement typeElement = aptUtils.elementUtils.getTypeElement(className);

            final EntityMetaCodeGen builder = new EntityMetaCodeGen(aptUtils);
            final List<FieldParser.FieldMetaSignature> parsingResults = getTypeParsingResults(aptUtils, typeElement, context);
            builder.buildEntityMeta(EntityType.TABLE, typeElement, context, parsingResults, emptyList());
        });
        failTestWithMessage(
                "The class 'info.archinnov.achilles.internals.sample_classes.parser.entity.TestEntityAsChildShadowingVariable' already contains a field with name 'value'",
                TestEntityAsChildShadowingVariable.class);
    }

}