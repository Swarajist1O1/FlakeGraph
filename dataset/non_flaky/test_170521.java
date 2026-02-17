class DummyClass_170521 {
    @Test
    public void testAnnotationScanControl() throws Exception
    {
        //check that a 2.5 webapp with configurationDiscovered will discover annotations
        TestableAnnotationConfiguration config25 = new TestableAnnotationConfiguration();
        WebAppContext context25 = new WebAppContext();
        context25.setClassLoader(Thread.currentThread().getContextClassLoader());
        context25.setAttribute(AnnotationConfiguration.MULTI_THREADED, Boolean.FALSE);
        context25.setAttribute(AnnotationConfiguration.MAX_SCAN_WAIT, 0);
        context25.setConfigurationDiscovered(false);
        context25.getMetaData().setWebDescriptor(new WebDescriptor(Resource.newResource(web25)));
        context25.getServletContext().setEffectiveMajorVersion(2);
        context25.getServletContext().setEffectiveMinorVersion(5);
        config25.configure(context25);
        config25.assertAnnotationDiscovery(false);

        //check that a 2.5 webapp discover annotations
        TestableAnnotationConfiguration config25b = new TestableAnnotationConfiguration();
        WebAppContext context25b = new WebAppContext();
        context25b.setClassLoader(Thread.currentThread().getContextClassLoader());
        context25b.setAttribute(AnnotationConfiguration.MULTI_THREADED, Boolean.FALSE);
        context25b.setAttribute(AnnotationConfiguration.MAX_SCAN_WAIT, 0);
        context25b.getMetaData().setWebDescriptor(new WebDescriptor(Resource.newResource(web25)));
        context25b.getServletContext().setEffectiveMajorVersion(2);
        context25b.getServletContext().setEffectiveMinorVersion(5);
        config25b.configure(context25b);
        config25b.assertAnnotationDiscovery(true);

        //check that a 3.x webapp with metadata true won't discover annotations
        TestableAnnotationConfiguration config31 = new TestableAnnotationConfiguration();
        WebAppContext context31 = new WebAppContext();
        context31.setClassLoader(Thread.currentThread().getContextClassLoader());
        context31.setAttribute(AnnotationConfiguration.MULTI_THREADED, Boolean.FALSE);
        context31.setAttribute(AnnotationConfiguration.MAX_SCAN_WAIT, 0);
        context31.getMetaData().setWebDescriptor(new WebDescriptor(Resource.newResource(web31true)));
        context31.getServletContext().setEffectiveMajorVersion(3);
        context31.getServletContext().setEffectiveMinorVersion(1);
        config31.configure(context31);
        config31.assertAnnotationDiscovery(false);

        //check that a 3.x webapp with metadata false will discover annotations
        TestableAnnotationConfiguration config31b = new TestableAnnotationConfiguration();
        WebAppContext context31b = new WebAppContext();
        context31b.setClassLoader(Thread.currentThread().getContextClassLoader());
        context31b.setAttribute(AnnotationConfiguration.MULTI_THREADED, Boolean.FALSE);
        context31b.setAttribute(AnnotationConfiguration.MAX_SCAN_WAIT, 0);
        context31b.getMetaData().setWebDescriptor(new WebDescriptor(Resource.newResource(web31false)));
        context31b.getServletContext().setEffectiveMajorVersion(3);
        context31b.getServletContext().setEffectiveMinorVersion(1);
        config31b.configure(context31b);
        config31b.assertAnnotationDiscovery(true);
    }

}