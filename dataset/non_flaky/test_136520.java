class DummyClass_136520 {
    @Test
    public void should_not_fail_for_non_frozen_tuple_map() throws Exception {
        setExec(aptUtils -> {
            final NestedTypeValidator2_1 strategy = new NestedTypeValidator2_1();
            final String className = TestEntityWithNestedTypes.class.getCanonicalName();
            final TypeName rawClass = ClassName.get(TestEntityWithNestedTypes.class);
            final TypeElement typeElement = aptUtils.elementUtils.getTypeElement(className);

            // private Tuple9<Integer, Integer, Integer, Integer, String, String, String, String, Map<Integer, String>> tupleMap;
            VariableElement elm = findFieldInType(typeElement, "tupleMap");
            final AnnotationTree annotationTree = AnnotationTree.buildFrom(aptUtils, globalParsingContext, elm);
            strategy.validate(aptUtils, annotationTree, "tupleMap", rawClass);
        });
        launchTest(TestEntityWithNestedTypes.class);
    }

}