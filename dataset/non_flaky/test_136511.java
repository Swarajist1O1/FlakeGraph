class DummyClass_136511 {
    @Test
    public void should_fail_for_non_frozen_map_listKey() throws Exception {
        setExec(aptUtils -> {
            final NestedTypeValidator2_1 strategy = new NestedTypeValidator2_1();
            final String className = TestEntityWithNestedTypes.class.getCanonicalName();
            final TypeName rawClass = ClassName.get(TestEntityWithNestedTypes.class);
            final TypeElement typeElement = aptUtils.elementUtils.getTypeElement(className);

            // private Map<List<Integer>, String> mapListKey;
            VariableElement elm = findFieldInType(typeElement, "mapListKey");
            final AnnotationTree annotationTree = AnnotationTree.buildFrom(aptUtils, globalParsingContext, elm);
            strategy.validate(aptUtils, annotationTree, "mapListKey", rawClass);
        });
        failTestWithMessage("Map key of type collection/UDT " +
                "'java.util.List<java.lang.Integer>' " +
                "in 'mapListKey' " +
                "of class 'info.archinnov.achilles.internals.sample_classes.parser.strategy.TestEntityWithNestedTypes' " +
                "should be annotated with @Frozen", TestEntityWithNestedTypes.class);
    }

}