class DummyClass_136524 {
    @Test
    public void should_fail_validating_index_depth_gt_2() throws Exception {
        setExec(aptUtils -> {
            final NestedTypeValidator2_1 strategy = new NestedTypeValidator2_1();
            final String className = TestEntityWithNestedTypes.class.getCanonicalName();
            final TypeName rawClass = ClassName.get(TestEntityWithNestedTypes.class);
            final TypeElement typeElement = aptUtils.elementUtils.getTypeElement(className);

            // private Map<Integer, @Frozen Map<Integer, @Index List<String>>> nestedIndex;
            VariableElement elm = findFieldInType(typeElement, "nestedIndex");
            final AnnotationTree annotationTree = AnnotationTree.buildFrom(aptUtils, globalParsingContext, elm);
            strategy.validate(aptUtils, annotationTree, "nestedIndex", rawClass);
        });
        failTestWithMessage("@Index annotation cannot be nested for depth > 2 for field 'nestedIndex' of class 'info.archinnov.achilles.internals.sample_classes.parser.strategy.TestEntityWithNestedTypes'",
                TestEntityWithNestedTypes.class);
    }

}