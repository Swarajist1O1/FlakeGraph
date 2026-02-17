class DummyClass_136502 {
    @Test
    public void should_fail_for_non_frozen_list_udt() throws Exception {
        setExec(aptUtils -> {
            final NestedTypeValidator2_1 strategy = new NestedTypeValidator2_1();
            final String className = TestEntityWithNestedTypes.class.getCanonicalName();
            final TypeName rawClass = ClassName.get(TestEntityWithNestedTypes.class);
            final TypeElement typeElement = aptUtils.elementUtils.getTypeElement(className);

            // private List<TestUDT> listUdt;
            VariableElement elm = findFieldInType(typeElement, "listUdt");
            final AnnotationTree annotationTree = AnnotationTree.buildFrom(aptUtils, globalParsingContext, elm);
            strategy.validate(aptUtils, annotationTree, "listUdt", rawClass);
        });
        failTestWithMessage("collections/array type/UDT " +
                "'info.archinnov.achilles.internals.sample_classes.parser.field.TestUDT' " +
                "in 'listUdt' " +
                "of class 'info.archinnov.achilles.internals.sample_classes.parser.strategy.TestEntityWithNestedTypes' " +
                "should be annotated with @Frozen", TestEntityWithNestedTypes.class);
    }

}