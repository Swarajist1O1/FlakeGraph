class DummyClass_136500 {
    @Test
    public void should_not_fail_for_non_frozen_tupleValue() throws Exception {
        setExec(aptUtils -> {
            final NestedTypeValidator2_1 strategy = new NestedTypeValidator2_1();
            final String className = TestEntityWithNestedTypes.class.getCanonicalName();
            final TypeName rawClass = ClassName.get(TestEntityWithNestedTypes.class);
            final TypeElement typeElement = aptUtils.elementUtils.getTypeElement(className);

            // private TupleValue tupleValue;
            VariableElement elm = findFieldInType(typeElement, "tupleValue");
            final AnnotationTree annotationTree = AnnotationTree.buildFrom(aptUtils, globalParsingContext, elm);
            strategy.validate(aptUtils, annotationTree, "tupleValue", rawClass);
        });
        launchTest(TestEntityWithNestedTypes.class);
    }

}