class DummyClass_136545 {
    @Test
    public void should_fail_building_class_with_no_public_constructor() throws Exception {
        setExec(aptUtils -> {
            final String className = TestEntityWithNoPublicConstructor.class.getCanonicalName();
            final TypeElement typeElement = aptUtils.elementUtils.getTypeElement(className);

            final EntityMetaCodeGen builder = new EntityMetaCodeGen(aptUtils);
            final List<FieldParser.FieldMetaSignature> parsingResults = getTypeParsingResults(aptUtils, typeElement, context);
            builder.buildEntityMeta(EntityType.TABLE, typeElement, context, parsingResults, emptyList());
        });
        failTestWithMessage(
                "Bean type 'info.archinnov.achilles.internals.sample_classes.parser.entity.TestEntityWithNoPublicConstructor' " +
                        "should have either a public no-args constructor or ONE custom constructor with annotation @EntityCreator",
                TestEntityWithNoPublicConstructor.class);
    }

}