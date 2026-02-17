class DummyClass_170516 {
    @Test
    public void testJep238MultiReleaseInJarJDK10() throws Exception
    {
        File jdk10Jar = MavenTestingUtils.getTestResourceFile("jdk10/multirelease-10.jar");
        AnnotationParser parser = new AnnotationParser();
        DuplicateClassScanHandler handler = new DuplicateClassScanHandler();
        Set<Handler> handlers = Collections.singleton(handler);
        parser.parse(handlers, new PathResource(jdk10Jar));
        // Should throw no exceptions
    }

}