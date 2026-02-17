class DummyClass_170512 {
    @Test
    public void testMultiAnnotation() throws Exception
    {
        String[] classNames = new String[]{"org.eclipse.jetty.annotations.ClassB"};
        AnnotationParser parser = new AnnotationParser();

        class MultiAnnotationHandler extends AnnotationParser.AbstractHandler
        {
            @Override
            public void handle(ClassInfo info, String annotation)
            {
                if (annotation == null || !"org.eclipse.jetty.annotations.Multi".equals(annotation))
                    return;
                assertTrue("org.eclipse.jetty.annotations.ClassB".equals(info.getClassName()));
            }

}