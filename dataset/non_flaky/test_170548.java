class DummyClass_170548 {
    @AfterEach
    public void destroy() throws Exception
    {
        comp.destroySubcontext("env");
    }

}