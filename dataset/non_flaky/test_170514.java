class DummyClass_170514 {
    @Test
    public void testModuleInfoClassInJar() throws Exception
    {
        File badClassesJar = MavenTestingUtils.getTestResourceFile("jdk9/slf4j-api-1.8.0-alpha2.jar");
        AnnotationParser parser = new AnnotationParser();
        Set<Handler> emptySet = Collections.emptySet();
        parser.parse(emptySet, badClassesJar.toURI());
        // Should throw no exceptions, and happily skip the module-info.class files
    }

}