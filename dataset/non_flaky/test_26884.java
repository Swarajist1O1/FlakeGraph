class DummyClass_26884 {
    @Test
    public void testList() throws Exception {
        bindList();

        NamingEnumeration<NameClassPair> results = namingContext.list(new CompositeName());
        checkListResults(results);

        //the same with security permissions
        results = (NamingEnumeration<NameClassPair>) testActionPermission(JndiPermission.ACTION_LIST, namingContext, null);
        checkListResults(results);
    }

}