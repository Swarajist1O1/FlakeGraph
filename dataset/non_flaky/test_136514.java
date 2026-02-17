class DummyClass_136514 {
    @Test
    public void should_not_fail_for_non_frozen_map_tupleValueKey() throws Exception {
        setExec(aptUtils -> {
            final NestedTypeValidator2_1 strategy = new NestedTypeValidator2_1();
            final String className = TestEntityWithNestedTypes.class.getCanonicalName();
            final TypeName rawClass = ClassName.get(TestEntityWithNestedTypes.class);
            final TypeElement typeElement = aptUtils.elementUtils.getTypeElement(className);

            // private Map<TupleValue, String> mapTupleValueKey;
            VariableElement elm = findFieldInType(typeElement, "mapTupleValueKey");
            final AnnotationTree annotationTree = AnnotationTree.buildFrom(aptUtils, globalParsingContext, elm);
            strategy.validate(aptUtils, annotationTree, "mapTupleValueKey", rawClass);
        });
        launchTest(TestEntityWithNestedTypes.class);
    }

}