class DummyClass_136516 {
    @Test
    public void should_not_fail_for_non_frozen_tuple_list() throws Exception {
        setExec(aptUtils -> {
            final NestedTypeValidator2_1 strategy = new NestedTypeValidator2_1();
            final String className = TestEntityWithNestedTypes.class.getCanonicalName();
            final TypeName rawClass = ClassName.get(TestEntityWithNestedTypes.class);
            final TypeElement typeElement = aptUtils.elementUtils.getTypeElement(className);

            // private Tuple5<Integer, List<String>, Integer, Integer, String> tupleList;
            VariableElement elm = findFieldInType(typeElement, "tupleList");
            final AnnotationTree annotationTree = AnnotationTree.buildFrom(aptUtils, globalParsingContext, elm);
            strategy.validate(aptUtils, annotationTree, "tupleList", rawClass);
        });
        launchTest(TestEntityWithNestedTypes.class);
    }

}