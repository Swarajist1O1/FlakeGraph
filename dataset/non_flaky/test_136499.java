class DummyClass_136499 {
    @Test
    public void should_fail_for_non_frozen_udtValue() throws Exception {
        setExec(aptUtils -> {
            final NestedTypeValidator2_1 strategy = new NestedTypeValidator2_1();
            final String className = TestEntityWithNestedTypes.class.getCanonicalName();
            final TypeName rawClass = ClassName.get(TestEntityWithNestedTypes.class);
            final TypeElement typeElement = aptUtils.elementUtils.getTypeElement(className);

            // private UDTValue udtValue;
            VariableElement elm = findFieldInType(typeElement, "udtValue");
            final AnnotationTree annotationTree = AnnotationTree.buildFrom(aptUtils, globalParsingContext, elm);
            strategy.validate(aptUtils, annotationTree, "udtValue", rawClass);
        });
        failTestWithMessage("UDTValue " +
                "in field 'udtValue' " +
                "of class 'info.archinnov.achilles.internals.sample_classes.parser.strategy.TestEntityWithNestedTypes' " +
                "should be annotated with @Frozen", TestEntityWithNestedTypes.class);
    }

}