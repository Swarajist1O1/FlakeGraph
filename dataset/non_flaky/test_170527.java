class DummyClass_170527 {
    @Test
    public void testDiscoveredTrueWithSCIs() throws Exception
    {
        ClassLoader old = Thread.currentThread().getContextClassLoader();
        Thread.currentThread().setContextClassLoader(webAppLoader);
        try
        {
            //test 2.5 webapp with configurationDiscovered=true loads both server and webapp scis
            AnnotationConfiguration config = new AnnotationConfiguration();
            WebAppContext context = new WebAppContext();
            List<ServletContainerInitializer> scis;
            context.setConfigurationDiscovered(true);
            context.setClassLoader(webAppLoader);
            context.getMetaData().setWebDescriptor(new WebDescriptor(Resource.newResource(web25)));
            context.getMetaData().setWebInfClassesResources(classes);
            context.getMetaData().addWebInfResource(Resource.newResource(testSciJar.toURI().toURL()));
            context.getServletContext().setEffectiveMajorVersion(2);
            context.getServletContext().setEffectiveMinorVersion(5);
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