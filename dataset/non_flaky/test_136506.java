class DummyClass_136506 {
    @Test
    public void should_fail_for_non_frozen_map_list() throws Exception {
        setExec(aptUtils -> {
            final NestedTypeValidator2_1 strategy = new NestedTypeValidator2_1();
            final String className = TestEntityWithNestedTypes.class.getCanonicalName();
            final TypeName rawClass = ClassName.get(TestEntityWithNestedTypes.class);
            final TypeElement typeElement = aptUtils.elementUtils.getTypeElement(className);

            // private Map<Integer, List<String>> mapList;
            VariableElement elm = findFieldInType(typeElement, "mapList");
            final AnnotationTree annotationTree = AnnotationTree.buildFrom(aptUtils, globalParsingContext, elm);
            strategy.validate(aptUtils, annotationTree, "mapList", rawClass);
        });
        failTestWithMessage("collections/array type/UDT " +
                "'java.util.List<java.lang.String>' " +
                "in 'mapList' " +
                "of class 'info.archinnov.achilles.internals.sample_classes.parser.strategy.TestEntityWithNestedTypes' " +
                "should be annotated with @Frozen", TestEntityWithNestedTypes.class);
    }

}