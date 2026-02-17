class DummyClass_136519 {
    @Test
    public void should_not_fail_for_non_frozen_tuple_tupleValue() throws Exception {
        setExec(aptUtils -> {
            final NestedTypeValidator2_1 strategy = new NestedTypeValidator2_1();
            final String className = TestEntityWithNestedTypes.class.getCanonicalName();
            final TypeName rawClass = ClassName.get(TestEntityWithNestedTypes.class);
            final TypeElement typeElement = aptUtils.elementUtils.getTypeElement(className);

            // private Tuple8<Integer, Integer, Integer, Integer, String, String, String, TupleValue> tupleTupleValue;
            VariableElement elm = findFieldInType(typeElement, "tupleTupleValue");
            final AnnotationTree annotationTree = AnnotationTree.buildFrom(aptUtils, globalParsingContext, elm);
            strategy.validate(aptUtils, annotationTree, "tupleTupleValue", rawClass);
        });
        launchTest(TestEntityWithNestedTypes.class);
    }

}