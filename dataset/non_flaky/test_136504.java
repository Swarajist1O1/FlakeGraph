class DummyClass_136504 {
    @Test
    public void should_not_fail_for_non_frozen_list_tupleValue() throws Exception {
        setExec(aptUtils -> {
            final NestedTypeValidator2_1 strategy = new NestedTypeValidator2_1();
            final String className = TestEntityWithNestedTypes.class.getCanonicalName();
            final TypeName rawClass = ClassName.get(TestEntityWithNestedTypes.class);
            final TypeElement typeElement = aptUtils.elementUtils.getTypeElement(className);

            // private List<TupleValue> listTupleValue;
            VariableElement elm = findFieldInType(typeElement, "listTupleValue");
            final AnnotationTree annotationTree = AnnotationTree.buildFrom(aptUtils, globalParsingContext, elm);
            strategy.validate(aptUtils, annotationTree, "listTupleValue", rawClass);
        });
        launchTest(TestEntityWithNestedTypes.class);
    }

}