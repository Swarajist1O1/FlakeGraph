class DummyClass_136517 {
    @Test
    public void should_not_fail_for_non_frozen_tuple_udt() throws Exception {
        setExec(aptUtils -> {
            final NestedTypeValidator2_1 strategy = new NestedTypeValidator2_1();
            final String className = TestEntityWithNestedTypes.class.getCanonicalName();
            final TypeName rawClass = ClassName.get(TestEntityWithNestedTypes.class);
            final TypeElement typeElement = aptUtils.elementUtils.getTypeElement(className);

            // private Tuple6<Integer, Integer, Integer, Integer, String, TestUDT> tupleUDT;
            VariableElement elm = findFieldInType(typeElement, "tupleUDT");
            final AnnotationTree annotationTree = AnnotationTree.buildFrom(aptUtils, globalParsingContext, elm);
            strategy.validate(aptUtils, annotationTree, "tupleUDT", rawClass);
        });
        launchTest(TestEntityWithNestedTypes.class);
    }

}