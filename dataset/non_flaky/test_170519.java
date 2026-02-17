class DummyClass_170519 {
    @Test
    public void testScanDuplicateClasses() throws Exception
    {
        Resource testJar = Resource.newResource(MavenTestingUtils.getTestResourceFile("tinytest.jar"));
        File testClasses = new File(MavenTestingUtils.getTargetDir(), "test-classes");
        AnnotationParser parser = new AnnotationParser();
        DuplicateClassScanHandler handler = new DuplicateClassScanHandler();
        Set<Handler> handlers = Collections.singleton(handler);
        parser.parse(handlers, testJar);
        parser.parse(handlers, Resource.newResource(testClasses));
        List<String> locations = handler.getParsedList("org.acme.ClassOne");
        assertNotNull(locations);
        assertEquals(2, locations.size());
        assertTrue(!(locations.get(0).equals(locations.get(1))));
    }

}