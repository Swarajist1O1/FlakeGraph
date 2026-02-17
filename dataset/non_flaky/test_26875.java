class DummyClass_26875 {
    @Test
    public void testLookupNameNotFound() throws Exception {
        try {
            namingContext.lookup(new CompositeName("test"));
            fail("Should have thrown and NameNotFoundException");
        } catch (NameNotFoundException expected) {
        }

        //the same with security permissions
        try {
            testActionPermission(JndiPermission.ACTION_LOOKUP, namingContext, "test");
            fail("Should have thrown and NameNotFoundException with appropriate permissions");
        } catch (NameNotFoundException expected) {
        }
    }

}