class DummyClass_170538 {
    @Test
    public void testIsIntrospectable() throws Exception
    {
        try (StacklessLogging ignore = new StacklessLogging(AnnotationIntrospector.class))
        {
            WebAppContext wac = new WebAppContext();
            AnnotationIntrospector introspector = new AnnotationIntrospector(wac);
            //can't introspect nothing
            assertFalse(introspector.isIntrospectable(null, null));

            //can introspect if no metadata to say otherwise
            assertTrue(introspector.isIntrospectable(new Object(), null));

            //can introspect if metdata isn't a BaseHolder
            assertTrue(introspector.isIntrospectable(new Object(), new Object()));

            //an EMBEDDED sourced servlet can be introspected
            ServletHolder holder = new ServletHolder();
            holder.setHeldClass(ServletE.class);
            assertTrue(introspector.isIntrospectable(new ServletE(), holder));

            //a JAVAX API sourced servlet can be introspected
            holder = new ServletHolder(Source.JAVAX_API);
            holder.setHeldClass(ServletE.class);
            assertTrue(introspector.isIntrospectable(new ServletE(), holder));

            //an ANNOTATION sourced servlet can be introspected
            holder = new ServletHolder(new Source(Source.Origin.ANNOTATION, ServletE.class.getName()));
            holder.setHeldClass(ServletE.class);
            assertTrue(introspector.isIntrospectable(new ServletE(), holder));

            //a DESCRIPTOR sourced servlet can be introspected if web.xml metdata-complete==false
            File file = MavenTestingUtils.getTestResourceFile("web31false.xml");
            Resource resource = Resource.newResource(file);
            wac.getMetaData().setWebDescriptor(new WebDescriptor(resource));
            holder = new ServletHolder(new Source(Source.Origin.DESCRIPTOR, resource.toString()));
            assertTrue(introspector.isIntrospectable(new ServletE(), holder));

            //a DESCRIPTOR sourced servlet can be introspected if web-fragment.xml medata-complete==false && web.xml metadata-complete==false
            file = MavenTestingUtils.getTestResourceFile("web-fragment4false.xml");
            resource = Resource.newResource(file);
            wac.getMetaData().addFragmentDescriptor(Resource.newResource(file.getParentFile()), new FragmentDescriptor(resource));
            holder = new ServletHolder(new Source(Source.Origin.DESCRIPTOR, resource.toString()));
            assertTrue(introspector.isIntrospectable(new ServletE(), holder));

            //a DESCRIPTOR sourced servlet cannot be introspected if web-fragment.xml medata-complete==true (&& web.xml metadata-complete==false)
            file = MavenTestingUtils.getTestResourceFile("web-fragment4true.xml");
            resource = Resource.newResource(file);
            wac.getMetaData().addFragmentDescriptor(Resource.newResource(file.getParentFile()), new FragmentDescriptor(resource));
            holder = new ServletHolder(new Source(Source.Origin.DESCRIPTOR, resource.toString()));
            assertFalse(introspector.isIntrospectable(new ServletE(), holder));

            //a DESCRIPTOR sourced servlet cannot be introspected if web.xml medata-complete==true
            file = MavenTestingUtils.getTestResourceFile("web31true.xml");
            resource = Resource.newResource(file);
            wac.getMetaData().setWebDescriptor(new WebDescriptor(resource));
            holder = new ServletHolder(new Source(Source.Origin.DESCRIPTOR, resource.toString()));
            assertFalse(introspector.isIntrospectable(new ServletE(), holder));
        }
    }

}