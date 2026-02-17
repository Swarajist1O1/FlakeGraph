class DummyClass_26869 {
    @Test
    public void testLookup() throws Exception {
        final Name name = new CompositeName("test");
        final Object object = new Object();
        namingStore.bind(name, object);

        Object result = namingContext.lookup(name);
        assertEquals(object, result);

        //the same with security permissions
        result = testActionPermission(JndiPermission.ACTION_LOOKUP, namingContext, "test");
        assertEquals(object, result);
    }

}