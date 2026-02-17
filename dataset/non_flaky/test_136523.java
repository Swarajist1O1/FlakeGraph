class DummyClass_136523 {
    @Test
    public void should_validate_index_depth_2_as_map_key() throws Exception {
        setExec(aptUtils -> {
            final NestedTypeValidator2_1 strategy = new NestedTypeValidator2_1();
            final String className = TestEntityWithNestedTypes.class.getCanonicalName();
            final TypeName rawClass = ClassName.get(TestEntityWithNestedTypes.class);
            final TypeElement typeElement = aptUtils.elementUtils.getTypeElement(className);

            // private Map<@Index Integer, @Frozen List<String>> indexedMapKey;
            VariableElement elm = findFieldInType(typeElement, "indexedMapKey");
            final AnnotationTree annotationTree = AnnotationTree.buildFrom(aptUtils, globalParsingContext, elm);
            strategy.validate(aptUtils, annotationTree, "indexedMapKey", rawClass);
        });
        launchTest(TestEntityWithNestedTypes.class);
    }

}