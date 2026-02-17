class DummyClass_136508 {
    @Test
    public void should_not_fail_for_non_frozen_map_tuple() throws Exception {
        setExec(aptUtils -> {
            final NestedTypeValidator2_1 strategy = new NestedTypeValidator2_1();
            final String className = TestEntityWithNestedTypes.class.getCanonicalName();
            final TypeName rawClass = ClassName.get(TestEntityWithNestedTypes.class);
            final TypeElement typeElement = aptUtils.elementUtils.getTypeElement(className);

            // private Map<Integer, Tuple2<Integer, String>> mapTuple;
            VariableElement elm = findFieldInType(typeElement, "mapTuple");
            final AnnotationTree annotationTree = AnnotationTree.buildFrom(aptUtils, globalParsingContext, elm);
            strategy.validate(aptUtils, annotationTree, "mapTuple", rawClass);
        });
        launchTest(TestEntityWithNestedTypes.class);
    }

}