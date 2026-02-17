class DummyClass_136512 {
    @Test
    public void should_fail_for_non_frozen_map_udtKey() throws Exception {
        setExec(aptUtils -> {
            final NestedTypeValidator2_1 strategy = new NestedTypeValidator2_1();
            final String className = TestEntityWithNestedTypes.class.getCanonicalName();
            final TypeName rawClass = ClassName.get(TestEntityWithNestedTypes.class);
            final TypeElement typeElement = aptUtils.elementUtils.getTypeElement(className);

            // private Map<TestUDT, String> mapUdtKey;
            VariableElement elm = findFieldInType(typeElement, "mapUdtKey");
            final AnnotationTree annotationTree = AnnotationTree.buildFrom(aptUtils, globalParsingContext, elm);
            strategy.validate(aptUtils, annotationTree, "mapUdtKey", rawClass);
        });
        failTestWithMessage("Map key of type collection/UDT " +
                "'info.archinnov.achilles.internals.sample_classes.parser.field.TestUDT' " +
                "in 'mapUdtKey' " +
                "of class 'info.archinnov.achilles.internals.sample_classes.parser.strategy.TestEntityWithNestedTypes' " +
                "should be annotated with @Frozen", TestEntityWithNestedTypes.class);
    }

}