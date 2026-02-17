class DummyClass_170542 {
    @Test
    public void testWebServletAnnotationReplaceDefault() throws Exception
    {
        //if the existing servlet mapping TO A DIFFERENT SERVLET IS from a default descriptor we
        //DO allow the annotation to replace the mapping.
        WebAppContext wac = new WebAppContext();
        ServletHolder defaultServlet = new ServletHolder();
        defaultServlet.setClassName("org.eclipse.jetty.servlet.DefaultServlet");
        defaultServlet.setName("default");
        wac.getServletHandler().addServlet(defaultServlet);

        ServletMapping m = new ServletMapping();
        m.setPathSpec("/");
        m.setServletName("default");
        m.setFromDefaultDescriptor(true);  //this mapping will be from a default descriptor
        wac.getServletHandler().addServletMapping(m);

        ServletMapping m2 = new ServletMapping();
        m2.setPathSpec("/other");
        m2.setServletName("default");
        m2.setFromDefaultDescriptor(true);  //this mapping will be from a default descriptor
        wac.getServletHandler().addServletMapping(m2);

        WebServletAnnotation annotation = new WebServletAnnotation(wac, "org.eclipse.jetty.annotations.ServletD", null);
        annotation.apply();

        //test that only the mapping for "/" was removed from the mappings to the default servlet
        ServletMapping[] resultMappings = wac.getServletHandler().getServletMappings();
        assertNotNull(resultMappings);
        assertEquals(2, resultMappings.length);
        for (ServletMapping r : resultMappings)
        {
            if (r.getServletName().equals("default"))
            {
                assertEquals(1, r.getPathSpecs().length);
                assertEquals("/other", r.getPathSpecs()[0]);
            }
            else if (r.getServletName().equals("DServlet"))
            {
                assertEquals(2, r.getPathSpecs().length);
                for (String p : r.getPathSpecs())
                {
                    if (!p.equals("/") && !p.equals("/bah/*"))
                        fail("Unexpected path");
                }
            }
            else
                fail("Unexpected servlet mapping: " + r);
        }
    }

}