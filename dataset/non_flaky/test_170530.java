class DummyClass_170530 {
    @Test
    public void testRolesAllowedWithTransportGuarantee() throws Exception
    {
        //Assume we found 1 servlet with annotation with roles defined and
        //and a TransportGuarantee

        WebAppContext wac = makeWebAppContext(RolesServlet.class.getCanonicalName(), "rolesServlet", new String[]{
            "/foo/*", "*.foo"
        });

        ServletSecurityAnnotationHandler annotationHandler = new ServletSecurityAnnotationHandler(wac);
        AnnotationIntrospector introspector = new AnnotationIntrospector(wac);
        introspector.registerHandler(annotationHandler);

        //set up the expected outcomes:compareResults
        //1 ConstraintMapping per ServletMapping
        Constraint expectedConstraint = new Constraint();
        expectedConstraint.setAuthenticate(true);
        expectedConstraint.setRoles(new String[]{"tom", "dick", "harry"});
        expectedConstraint.setDataConstraint(Constraint.DC_CONFIDENTIAL);

        ConstraintMapping[] expectedMappings = new ConstraintMapping[2];
        expectedMappings[0] = new ConstraintMapping();
        expectedMappings[0].setConstraint(expectedConstraint);
        expectedMappings[0].setPathSpec("/foo/*");

        expectedMappings[1] = new ConstraintMapping();
        expectedMappings[1].setConstraint(expectedConstraint);
        expectedMappings[1].setPathSpec("*.foo");
        introspector.introspect(new RolesServlet(), null);
        compareResults(expectedMappings, ((ConstraintAware)wac.getSecurityHandler()).getConstraintMappings());
    }

}