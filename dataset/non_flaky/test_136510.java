class DummyClass_136510 {
    @Test
    public void should_fail_for_non_frozen_map_udtValue() throws Exception {
        setExec(aptUtils -> {
            final NestedTypeValidator2_1 strategy = new NestedTypeValidator2_1();
            final String className = TestEntityWithNestedTypes.class.getCanonicalName();
            final TypeName rawClass = ClassName.get(TestEntityWithNestedTypes.class);
            final TypeElement typeElement = aptUtils.elementUtils.getTypeElement(className);

            // private Map<Integer, UDTValue> mapUDTValue;
            VariableElement elm = findFieldInType(typeElement, "mapUDTValue");
            final AnnotationTree annotationTree = AnnotationTree.buildFrom(aptUtils, globalParsingContext, elm);
            strategy.validate(aptUtils, annotationTree, "mapUDTValue", rawClass);
        });
        failTestWithMessage("collections/array type/UDT " +
                "'com.datastax.driver.core.UDTValue' " +
                "in 'mapUDTValue' " +
                "of class 'info.archinnov.achilles.internals.sample_classes.parser.strategy.TestEntityWithNestedTypes' " +
                "should be annotated with @Frozen", TestEntityWithNestedTypes.class);
    }

}