class DummyClass_26886 {
    @Test
    public void testListBindingsNameNotFound() throws Exception {
        try {
            namingContext.listBindings(new CompositeName("test"));
            fail("Should have thrown and NameNotFoundException");
        } catch (NameNotFoundException expected) {
        }

        //the same with security permissions
        try {
            testActionPermission(JndiPermission.ACTION_LIST_BINDINGS, namingContext, "test");
            fail("Should have thrown and NameNotFoundException with appropriate permissions");
        } catch (NameNotFoundException expected) {
        }
    }

}