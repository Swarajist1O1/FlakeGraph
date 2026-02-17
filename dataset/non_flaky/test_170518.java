class DummyClass_170518 {
    @Test
    public void testScanDuplicateClassesInJars() throws Exception
    {
        Resource testJar = Resource.newResource(MavenTestingUtils.getTestResourceFile("tinytest.jar"));
        Resource testJar2 = Resource.newResource(MavenTestingUtils.getTestResourceFile("tinytest_copy.jar"));
        AnnotationParser parser = new AnnotationParser();
        DuplicateClassScanHandler handler = new DuplicateClassScanHandler();
        Set<Handler> handlers = Collections.singleton(handler);
        parser.parse(handlers, testJar);
        parser.parse(handlers, testJar2);
        List<String> locations = handler.getParsedList("org.acme.ClassOne");
        assertNotNull(locations);
        assertEquals(2, locations.size());
        assertTrue(!(locations.get(0).equals(locations.get(1))));
    }

}