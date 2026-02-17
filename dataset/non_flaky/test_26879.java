class DummyClass_26879 {
    @Test
    public void testUnbind() throws Exception {
        final Name name = new CompositeName("test");
        final Object value = new Object();
        namingStore.bind(name, value);
        namingContext.unbind(name);
        try {
            namingStore.lookup(name);
            fail("Should have thrown name not found");
        } catch (NameNotFoundException expect) {}

        //the same with security permissions
        testActionPermission(JndiPermission.ACTION_BIND, namingContext, "test", value);
        testActionPermission(JndiPermission.ACTION_UNBIND, namingContext, "test");
        try {
            namingStore.lookup(name);
            fail("Should have thrown name not found");
        } catch (NameNotFoundException expect) {}
    }

}