class DummyClass_170515 {
    @Test
    public void testJep238MultiReleaseInJar() throws Exception
    {
        File badClassesJar = MavenTestingUtils.getTestResourceFile("jdk9/log4j-api-2.9.0.jar");
        AnnotationParser parser = new AnnotationParser();
        Set<Handler> emptySet = Collections.emptySet();
        parser.parse(emptySet, badClassesJar.toURI());
        // Should throw no exceptions, and skip the META-INF/versions/9/* files
    }

}