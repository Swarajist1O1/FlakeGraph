class DummyClass_97759 {
    @Test
    public void testReturnedTypesFromApplication() {
        final List<SourceType<Type>> sourceTypes = JaxrsApplicationScanner.scanJaxrsApplication(TestApplication.class, null);
        List<Type> types = getTypes(sourceTypes);
        final List<Type> expectedTypes = Arrays.<Type>asList(
                TestApplication.class,
                TestResource1.class
        );
        assertHasSameItems(expectedTypes, types);
    }

}