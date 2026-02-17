class DummyClass_156124 {
    @Test
    public void constructorReference() {
        String testClass = "soot.lambdaMetaFactory.Issue1367";

        final SootMethod target = prepareTarget(
                methodSigFromComponents(testClass, "java.util.function.Supplier", "constructorReference"),
                testClass,
                "java.util.function.Function");

        validateAllBodies(target.getDeclaringClass());
    }

}