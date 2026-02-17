class DummyClass_26880 {
    @Test
    public void testCreateSubcontext() throws Exception {
        assertTrue(namingContext.createSubcontext(new CompositeName("test")) instanceof NamingContext);

        //the same with security permissions
        assertTrue(testActionPermission(JndiPermission.ACTION_CREATE_SUBCONTEXT, namingContext, "securitytest") instanceof NamingContext);
    }

}