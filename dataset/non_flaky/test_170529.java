class DummyClass_170529 {
    @Test
    public void testPermitAll() throws Exception
    {
        //Assume we found 1 servlet with a @ServletSecurity security annotation
        WebAppContext wac = makeWebAppContext(PermitServlet.class.getCanonicalName(), "permitServlet", new String[]{
            "/foo/*", "*.foo"
        });

        ServletSecurityAnnotationHandler annotationHandler = new ServletSecurityAnnotationHandler(wac);
        AnnotationIntrospector introspector = new AnnotationIntrospector(wac);
        introspector.registerHandler(annotationHandler);

        //set up the expected outcomes - no constraints at all as per Servlet Spec 3.1 pg 129
        //1 ConstraintMapping per ServletMapping pathSpec

        ConstraintMapping[] expectedMappings = new ConstraintMapping[]{};
        PermitServlet permit = new PermitServlet();
        introspector.introspect(permit, null);

        compareResults(expectedMappings, ((ConstraintAware)wac.getSecurityHandler()).getConstraintMappings());
    }

}