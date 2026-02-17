class DummyClass_170534 {
    @AfterEach
    public void destroy() throws Exception
    {
        classNames.clear();
        InitialContext ic = new InitialContext();
        Context comp = (Context)ic.lookup("java:comp");
        comp.destroySubcontext("env");
    }

}