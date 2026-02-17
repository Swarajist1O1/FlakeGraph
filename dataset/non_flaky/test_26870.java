class DummyClass_26870 {
    @Test
    public void testLookupReference() throws Exception {
        final Name name = new CompositeName("test");
        final Reference reference = new Reference(String.class.getName(), new StringRefAddr("blah", "test"), TestObjectFactory.class.getName(), null);
        namingStore.bind(name, reference);

        Object result = namingContext.lookup(name);
        assertEquals("test", result);

        //the same with security permissions
        result = testActionPermission(JndiPermission.ACTION_LOOKUP, namingContext, "test");
        assertEquals("test", result);
    }

}