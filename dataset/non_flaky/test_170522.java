class DummyClass_170522 {
    @Test
    public void testServerAndWebappSCIs() throws Exception
    {
        ClassLoader old = Thread.currentThread().getContextClassLoader();
        Thread.currentThread().setContextClassLoader(webAppLoader);

        try
        {
            AnnotationConfiguration config = new AnnotationConfiguration();
            WebAppContext context = new WebAppContext();
            List<ServletContainerInitializer> scis;

            //test 3.1 webapp loads both server and app scis
            context.setClassLoader(webAppLoader);
            context.getMetaData().addWebInfResource(Resource.newResource(testSciJar.toURI().toURL()));
            context.getMetaData().setWebDescriptor(new WebDescriptor(Resource.newResource(web31true)));
            context.getMetaData().setWebInfClassesResources(classes);
            context.getServletContext().setEffectiveMajorVersion(3);
            context.getServletContext().setEffectiveMinorVersion(1);
            scis = config.getNonExcludedInitializers(context);
            assertNotNull(scis);
            assertEquals(3, scis.size());
            assertEquals("com.acme.ServerServletContainerInitializer", scis.get(0).getClass().getName()); //container path
            assertEquals("com.acme.webinf.WebInfClassServletContainerInitializer", scis.get(1).getClass().getName()); // web-inf
            assertEquals("com.acme.initializer.FooInitializer", scis.get(2).getClass().getName()); //web-inf jar no web-fragment
        }
        finally
        {
            Thread.currentThread().setContextClassLoader(old);
        }
    }

}