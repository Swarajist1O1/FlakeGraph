class DummyClass_170526 {
    @Test
    public void testDiscoveredFalseWithSCIs() throws Exception
    {
        ClassLoader old = Thread.currentThread().getContextClassLoader();
        Thread.currentThread().setContextClassLoader(webAppLoader);
        try
        {
            //test 2.5 webapp with configurationDiscovered=false loads only server scis
            AnnotationConfiguration config = new AnnotationConfiguration();
            WebAppContext context = new WebAppContext();
            List<ServletContainerInitializer> scis;
            context.setConfigurationDiscovered(false);
            context.setClassLoader(webAppLoader);
            context.getMetaData().setWebDescriptor(new WebDescriptor(Resource.newResource(web25)));
            context.getMetaData().setWebInfClassesResources(classes);
            context.getMetaData().addWebInfResource(Resource.newResource(testSciJar.toURI().toURL()));
            context.getServletContext().setEffectiveMajorVersion(2);
            context.getServletContext().setEffectiveMinorVersion(5);
            scis = config.getNonExcludedInitializers(context);
            assertNotNull(scis);
            for (ServletContainerInitializer s : scis)
            {
                //should not have any of the web-inf lib scis in here
                assertFalse(s.getClass().getName().equals("com.acme.ordering.AcmeServletContainerInitializer"));
                assertFalse(s.getClass().getName().equals("com.acme.initializer.FooInitializer"));
                //NOTE: should also not have the web-inf classes scis in here either, but due to the
                //way the test is set up, the sci we're pretending is in web-inf classes will actually
                //NOT be loaded by the webapp's classloader, but rather by the junit classloader, so
                //it looks as if it is a container class.
            }
        }
        finally
        {
            Thread.currentThread().setContextClassLoader(old);
        }
    }

}