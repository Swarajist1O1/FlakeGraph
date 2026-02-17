class DummyClass_136507 {
    @Test
    public void should_fail_for_non_frozen_map_udt() throws Exception {
        setExec(aptUtils -> {
            final NestedTypeValidator2_1 strategy = new NestedTypeValidator2_1();
            final String className = TestEntityWithNestedTypes.class.getCanonicalName();
            final TypeName rawClass = ClassName.get(TestEntityWithNestedTypes.class);
            final TypeElement typeElement = aptUtils.elementUtils.getTypeElement(className);

            // private Map<Integer, TestUDT> mapUdt;
            VariableElement elm = findFieldInType(typeElement, "mapUdt");
            final AnnotationTree annotationTree = AnnotationTree.buildFrom(aptUtils, globalParsingContext, elm);
            strategy.validate(aptUtils, annotationTree, "mapUdt", rawClass);
        });
        failTestWithMessage("collections/array type/UDT " +
                "'info.archinnov.achilles.internals.sample_classes.parser.field.TestUDT' " +
                "in 'mapUdt' " +
                "of class 'info.archinnov.achilles.internals.sample_classes.parser.strategy.TestEntityWithNestedTypes' " +
                "should be annotated with @Frozen", TestEntityWithNestedTypes.class);
    }

}