class DummyClass_170551 {
    @Test
    public void testRunAsAnnotation() throws Exception
    {
        WebAppContext wac = new WebAppContext();
        
        //pre-add a servlet but not by descriptor
        ServletHolder holder = new ServletHolder();
        holder.setName("foo1");
        holder.setHeldClass(ServletC.class);
        holder.setInitOrder(1); //load on startup
        wac.getServletHandler().addServletWithMapping(holder, "/foo/*");
        
        //add another servlet of the same class, but as if by descriptor
        ServletHolder holder2 = new ServletHolder();
        holder2.setName("foo2");
        holder2.setHeldClass(ServletC.class);
        holder2.setInitOrder(1);
        wac.getServletHandler().addServletWithMapping(holder2, "/foo2/*");
        Resource fakeXml = Resource.newResource(new File(MavenTestingUtils.getTargetTestingDir("run-as"), "fake.xml"));
        wac.getMetaData().setOrigin(holder2.getName() + ".servlet.run-as", new WebDescriptor(fakeXml));
        
        AnnotationIntrospector parser = new AnnotationIntrospector(wac);
        RunAsAnnotationHandler handler = new RunAsAnnotationHandler(wac);
        parser.registerHandler(handler);
        parser.introspect(new ServletC(), null);
        
        assertEquals("admin", holder.getRunAsRole());
        assertEquals(null, holder2.getRunAsRole());
    }

}