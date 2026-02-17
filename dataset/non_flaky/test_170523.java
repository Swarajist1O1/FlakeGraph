class DummyClass_170523 {
    @Test
    public void testClassScanHandlersForSCIs() throws Exception
    {
        //test that SCIs with a @HandlesTypes that is an annotation registers
        //handlers for the scanning phase that will capture the class hierarchy,
        //and also capture all classes that contain the annotation
        ClassLoader old = Thread.currentThread().getContextClassLoader();
        Thread.currentThread().setContextClassLoader(webAppLoader);

        try
        {
            class MyAnnotationConfiguration extends AnnotationConfiguration
            {

                @Override
                public void createServletContainerInitializerAnnotationHandlers(WebAppContext context, List<ServletContainerInitializer> scis) throws Exception
                {
                    super.createServletContainerInitializerAnnotationHandlers(context, scis);
                    //check class hierarchy scanner handler is registered
                    assertNotNull(_classInheritanceHandler);
                    //check 
                    assertEquals(1, _containerInitializerAnnotationHandlers.size());
                    ContainerInitializerAnnotationHandler handler = _containerInitializerAnnotationHandlers.get(0);
                    assertThat(handler._holder.toString(), containsString("com.acme.initializer.FooInitializer"));
                    assertEquals("com.acme.initializer.Foo", handler._annotation.getName());
                }

}