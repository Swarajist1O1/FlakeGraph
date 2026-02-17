class DummyClass_26874 {
    @Test
    public void testLookupContextLink() throws Exception {
        final Name name = new CompositeName("test/value");
        namingStore.bind(name, "testValue");
        final Name linkName = new CompositeName("link");
        namingStore.bind(linkName, new LinkRef("./test"));
        Object result = namingContext.lookup("link/value");
        assertEquals("testValue", result);

        //the same with security permissions
        result = testActionPermission(JndiPermission.ACTION_LOOKUP, Arrays.asList(new JndiPermission("test", "lookup"),
                new JndiPermission("test/value", "lookup")), namingContext, "link/value");

        assertEquals("testValue", result);
    }

}