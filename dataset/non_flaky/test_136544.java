class DummyClass_136544 {
    @Test
    public void should_fail_building_abstract_class() throws Exception {
        setExec(aptUtils -> {
            final String className = TestEntityWithAbstractClass.class.getCanonicalName();
            final TypeElement typeElement = aptUtils.elementUtils.getTypeElement(className);

            final EntityMetaCodeGen builder = new EntityMetaCodeGen(aptUtils);
            final List<FieldParser.FieldMetaSignature> parsingResults = getTypeParsingResults(aptUtils, typeElement, context);
            builder.buildEntityMeta(EntityType.TABLE, typeElement, context, parsingResults, emptyList());

        });
        failTestWithMessage(
                "Bean type 'info.archinnov.achilles.internals.sample_classes.parser.entity.TestEntityWithAbstractClass' should not be abstract",
                TestEntityWithAbstractClass.class);
    }

}