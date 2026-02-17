class DummyClass_26885 {
    @Test
    public void testListWithContinuation() throws Exception {
        bindListWithContinuations();

        NamingEnumeration<NameClassPair> results = namingContext.list(new CompositeName("comp"));
        checkListWithContinuationsResults(results);

        //the same with security permissions
        results = (NamingEnumeration<NameClassPair>) testActionPermission(JndiPermission.ACTION_LIST, Arrays.asList(
                new JndiPermission("test", "list")), namingContext, "comp");

        checkListWithContinuationsResults(results);
    }

}