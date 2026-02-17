class DummyClass_26905 {
    @Test
    public void testInitialFactory() throws Exception {
        // Test with sys prop
        System.setProperty(Context.INITIAL_CONTEXT_FACTORY, InitialContextFactory.class.getName());
        InitialContext initialContext = new InitialContext();
        Context context = (Context) initialContext.lookup("");
        assertTrue(context instanceof NamingContext);

        // Test with builder
        if (!NamingManager.hasInitialContextFactoryBuilder()) {
            NamingManager.setInitialContextFactoryBuilder(new InitialContextFactoryBuilder());
        }
        initialContext = new InitialContext();
        context = (Context) initialContext.lookup("");
        assertTrue(context instanceof NamingContext);
    }

}