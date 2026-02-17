class DummyClass_26882 {
    @Test
    public void testRebindReferenceable() throws Exception {
        final Name name = new CompositeName("test");
        final TestObjectReferenceable referenceable = new TestObjectReferenceable("addr");
        namingContext.bind(name, referenceable);
        TestObjectReferenceable newReferenceable = new TestObjectReferenceable("newAddr");
        namingContext.rebind(name, newReferenceable);
        Object result = namingContext.lookup(name);
        assertEquals(newReferenceable.addr, result);

        //the same with security permissions
        newReferenceable = new TestObjectReferenceable("yetAnotherNewAddr");
        testActionPermission(JndiPermission.ACTION_REBIND, namingContext, "test", newReferenceable);
        result = namingContext.lookup(name);
        assertEquals(newReferenceable.addr, result);
    }

}