class DummyClass_136503 {
    @Test
    public void should_not_fail_for_non_frozen_list_tuple() throws Exception {
        setExec(aptUtils -> {
            final NestedTypeValidator2_1 strategy = new NestedTypeValidator2_1();
            final String className = TestEntityWithNestedTypes.class.getCanonicalName();
            final TypeName rawClass = ClassName.get(TestEntityWithNestedTypes.class);
            final TypeElement typeElement = aptUtils.elementUtils.getTypeElement(className);

            // private List<Tuple3<Integer, String, String>> listTuple;
            VariableElement elm = findFieldInType(typeElement, "listTuple");
            final AnnotationTree annotationTree = AnnotationTree.buildFrom(aptUtils, globalParsingContext, elm);
            strategy.validate(aptUtils, annotationTree, "listTuple", rawClass);
        });
        launchTest(TestEntityWithNestedTypes.class);
    }

}