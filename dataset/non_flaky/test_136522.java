class DummyClass_136522 {
    @Test
    public void should_validate_index_depth_2() throws Exception {
        setExec(aptUtils -> {
            final NestedTypeValidator2_1 strategy = new NestedTypeValidator2_1();
            final String className = TestEntityWithNestedTypes.class.getCanonicalName();
            final TypeName rawClass = ClassName.get(TestEntityWithNestedTypes.class);
            final TypeElement typeElement = aptUtils.elementUtils.getTypeElement(className);

            // private Map<Integer, @Frozen @Index List<String>> indexedMapList;
            VariableElement elm = findFieldInType(typeElement, "indexedMapList");
            final AnnotationTree annotationTree = AnnotationTree.buildFrom(aptUtils, globalParsingContext, elm);
            strategy.validate(aptUtils, annotationTree, "indexedMapList", rawClass);
        });
        launchTest(TestEntityWithNestedTypes.class);
    }

}