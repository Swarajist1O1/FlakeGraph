class DummyClass_170525 {
    @Test
    public void testRelativeOrderingWithSCIs() throws Exception
    {
        // test a 3.1 webapp with RELATIVE ORDERING loads sci from
        // equivalent of WEB-INF/classes first as well as container path

        ClassLoader old = Thread.currentThread().getContextClassLoader();

        File orderedFragmentJar = new File(jarDir, "test-sci-with-ordering.jar");
        assertTrue(orderedFragmentJar.exists());
        URLClassLoader orderedLoader = new URLClassLoader(new URL[]{
            orderedFragmentJar.toURI().toURL(), testSciJar.toURI().toURL(),
            targetClasses.getURI().toURL(), webInfClasses.getURI().toURL()
        },
            containerLoader);
        Thread.currentThread().setContextClassLoader(orderedLoader);

        try
        {
            AnnotationConfiguration config = new AnnotationConfiguration();
            WebAppContext context = new WebAppContext();
            List<ServletContainerInitializer> scis;
            context.setClassLoader(orderedLoader);
            context.getMetaData().setWebDescriptor(new WebDescriptor(Resource.newResource(web31true)));
            RelativeOrdering ordering = new RelativeOrdering(context.getMetaData());
            context.getMetaData().setOrdering(ordering);
            context.getMetaData().addWebInfResource(Resource.newResource(orderedFragmentJar.toURI().toURL()));
            context.getMetaData().addWebInfResource(Resource.newResource(testSciJar.toURI().toURL()));
            context.getMetaData().setWebInfClassesResources(classes);
            context.getMetaData().orderFragments();
            context.getServletContext().setEffectiveMajorVersion(3);
            context.getServletContext().setEffectiveMinorVersion(1);
            scis = config.getNonExcludedInitializers(context);
            assertNotNull(scis);
            assertEquals(4, scis.size());
            assertEquals("com.acme.ServerServletContainerInitializer", scis.get(0).getClass().getName()); //container path
            assertEquals("com.acme.webinf.WebInfClassServletContainerInitializer", scis.get(1).getClass().getName()); // web-inf
            assertEquals("com.acme.ordering.AcmeServletContainerInitializer", scis.get(2).getClass().getName()); // first
            assertEquals("com.acme.initializer.FooInitializer", scis.get(3).getClass().getName()); //other in ordering
        }
        finally
        {
            Thread.currentThread().setContextClassLoader(old);
        }
    }

}