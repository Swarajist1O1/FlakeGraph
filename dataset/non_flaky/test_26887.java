class DummyClass_26887 {
    @Test
    public void testListBindings() throws Exception {
        bindList();

        NamingEnumeration<Binding> results = namingContext.listBindings(new CompositeName());
        checkListResults(results);

        //the same with security permissions
        results = (NamingEnumeration<Binding>) testActionPermission(JndiPermission.ACTION_LIST_BINDINGS, namingContext, null);
        checkListResults(results);
    }

}