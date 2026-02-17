class DummyClass_97761 {
    @Test
    public void testWithParsingWithExplicitApplication() {
        final List<SourceType<Type>> sourceTypes = JaxrsApplicationScanner.scanJaxrsApplication(TestApplication.class, null);
        testWithParsing(sourceTypes, true);
    }

}