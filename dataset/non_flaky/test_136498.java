class DummyClass_136498 {
    @Test
    public void should_fail_for_non_frozen_udt() throws Exception {
        setExec(aptUtils -> {
            final NestedTypeValidator2_1 strategy = new NestedTypeValidator2_1();
            final String className = TestEntityWithNestedTypes.class.getCanonicalName();
            final TypeName rawClass = ClassName.get(TestEntityWithNestedTypes.class);
            final TypeElement typeElement = aptUtils.elementUtils.getTypeElement(className);

            // private TestUDT testUDT;
            VariableElement elm = findFieldInType(typeElement, "testUDT");
            final AnnotationTree annotationTree = AnnotationTree.buildFrom(aptUtils, globalParsingContext, elm);
            strategy.validate(aptUtils, annotationTree, "testUDT", rawClass);
        });
        failTestWithMessage("UDT class " +
                "'info.archinnov.achilles.internals.sample_classes.parser.field.TestUDT' " +
                "in field 'testUDT' " +
                "of class 'info.archinnov.achilles.internals.sample_classes.parser.strategy.TestEntityWithNestedTypes' " +
                "should be annotated with @Frozen", TestEntityWithNestedTypes.class);
    }

}