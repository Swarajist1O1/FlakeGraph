class DummyClass_136521 {
    @Test
    public void should_validate_index_depth_1() throws Exception {
        setExec(aptUtils -> {
            final NestedTypeValidator2_1 strategy = new NestedTypeValidator2_1();
            final String className = TestEntityWithNestedTypes.class.getCanonicalName();
            final TypeName rawClass = ClassName.get(TestEntityWithNestedTypes.class);
            final TypeElement typeElement = aptUtils.elementUtils.getTypeElement(className);

            // @Index private String indexedString;
            VariableElement elm = findFieldInType(typeElement, "indexedString");
            final AnnotationTree annotationTree = AnnotationTree.buildFrom(aptUtils, globalParsingContext, elm);
            strategy.validate(aptUtils, annotationTree, "indexedString", rawClass);
        });
        launchTest(TestEntityWithNestedTypes.class);
    }

}