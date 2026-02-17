class DummyClass_136518 {
    @Test
    public void should_not_fail_for_non_frozen_tuple_udtValue() throws Exception {
        setExec(aptUtils -> {
            final NestedTypeValidator2_1 strategy = new NestedTypeValidator2_1();
            final String className = TestEntityWithNestedTypes.class.getCanonicalName();
            final TypeName rawClass = ClassName.get(TestEntityWithNestedTypes.class);
            final TypeElement typeElement = aptUtils.elementUtils.getTypeElement(className);

            // private Tuple7<Integer, Integer, Integer, Integer, String, String, UDTValue> tupleUDTValue;
            VariableElement elm = findFieldInType(typeElement, "tupleUDTValue");
            final AnnotationTree annotationTree = AnnotationTree.buildFrom(aptUtils, globalParsingContext, elm);
            strategy.validate(aptUtils, annotationTree, "tupleUDTValue", rawClass);
        });
        launchTest(TestEntityWithNestedTypes.class);
    }

}