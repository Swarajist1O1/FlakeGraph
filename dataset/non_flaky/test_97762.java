class DummyClass_97762 {
    @Test
    public void testWithParsingWithDefaultApplication() {
        final List<SourceType<Type>> sourceTypes = JaxrsApplicationScanner.scanAutomaticJaxrsApplication(new ClassGraph().enableAllInfo().scan(), null);
        testWithParsing(sourceTypes, false);
    }

}