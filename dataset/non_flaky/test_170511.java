class DummyClass_170511 {
    @Test
    public void testSampleAnnotation() throws Exception
    {
        String[] classNames = new String[]{"org.eclipse.jetty.annotations.ClassA"};
        AnnotationParser parser = new AnnotationParser();

        class SampleAnnotationHandler extends AnnotationParser.AbstractHandler
        {
            private List<String> methods = Arrays.asList("a", "b", "c", "d", "l");

            @Override
            public void handle(ClassInfo info, String annotation)
            {
                if (annotation == null || !"org.eclipse.jetty.annotations.Sample".equals(annotation))
                    return;

                assertEquals("org.eclipse.jetty.annotations.ClassA", info.getClassName());
            }

        }
    }
}