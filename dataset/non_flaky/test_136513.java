class DummyClass_136513 {
    @Test
    public void should_not_fail_for_non_frozen_map_tupleKey() throws Exception {
        setExec(aptUtils -> {
            final NestedTypeValidator2_1 strategy = new NestedTypeValidator2_1();
            final String className = TestEntityWithNestedTypes.class.getCanonicalName();
            final TypeName rawClass = ClassName.get(TestEntityWithNestedTypes.class);
            final TypeElement typeElement = aptUtils.elementUtils.getTypeElement(className);

            // private Map<Tuple4<Integer, Integer, String, String>, String> mapTupleKey;
            VariableElement elm = findFieldInType(typeElement, "mapTupleKey");
            final AnnotationTree annotationTree = AnnotationTree.buildFrom(aptUtils, globalParsingContext, elm);
            strategy.validate(aptUtils, annotationTree, "mapTupleKey", rawClass);
        });
        launchTest(TestEntityWithNestedTypes.class);
    }

}