class DummyClass_170513 {
    @Test
    public void testHiddenFilesInJar() throws Exception
    {
        File badClassesJar = MavenTestingUtils.getTestResourceFile("bad-classes.jar");
        AnnotationParser parser = new AnnotationParser();
        Set<Handler> emptySet = Collections.emptySet();
        parser.parse(emptySet, badClassesJar.toURI());
        // only the valid classes inside bad-classes.jar should be parsed. If any invalid classes are parsed and exception would be thrown here
    }

}