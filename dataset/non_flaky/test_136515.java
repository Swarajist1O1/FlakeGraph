class DummyClass_136515 {
    @Test
    public void should_fail_for_non_frozen_map_udtValueKey() throws Exception {
        setExec(aptUtils -> {
            final NestedTypeValidator2_1 strategy = new NestedTypeValidator2_1();
            final String className = TestEntityWithNestedTypes.class.getCanonicalName();
            final TypeName rawClass = ClassName.get(TestEntityWithNestedTypes.class);
            final TypeElement typeElement = aptUtils.elementUtils.getTypeElement(className);

            // private Map<UDTValue, String> mapUDTValueKey;
            VariableElement elm = findFieldInType(typeElement, "mapUDTValueKey");
            final AnnotationTree annotationTree = AnnotationTree.buildFrom(aptUtils, globalParsingContext, elm);
            strategy.validate(aptUtils, annotationTree, "mapUDTValueKey", rawClass);
        });
        failTestWithMessage("Map key of type collection/UDT " +
                "'com.datastax.driver.core.UDTValue' " +
                "in 'mapUDTValueKey' " +
                "of class 'info.archinnov.achilles.internals.sample_classes.parser.strategy.TestEntityWithNestedTypes' " +
                "should be annotated with @Frozen", TestEntityWithNestedTypes.class);
    }

}