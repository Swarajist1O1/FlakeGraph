class DummyClass_136505 {
    @Test
    public void should_fail_for_non_frozen_list_udtValue() throws Exception {
        setExec(aptUtils -> {
            final NestedTypeValidator2_1 strategy = new NestedTypeValidator2_1();
            final String className = TestEntityWithNestedTypes.class.getCanonicalName();
            final TypeName rawClass = ClassName.get(TestEntityWithNestedTypes.class);
            final TypeElement typeElement = aptUtils.elementUtils.getTypeElement(className);

            // private List<UDTValue> listUdtValue;
            VariableElement elm = findFieldInType(typeElement, "listUdtValue");
            final AnnotationTree annotationTree = AnnotationTree.buildFrom(aptUtils, globalParsingContext, elm);
            strategy.validate(aptUtils, annotationTree, "listUdtValue", rawClass);
        });
        failTestWithMessage("collections/array type/UDT " +
                "'com.datastax.driver.core.UDTValue' " +
                "in 'listUdtValue' " +
                "of class 'info.archinnov.achilles.internals.sample_classes.parser.strategy.TestEntityWithNestedTypes' " +
                "should be annotated with @Frozen", TestEntityWithNestedTypes.class);
    }

}