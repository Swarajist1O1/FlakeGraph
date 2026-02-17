class DummyClass_170453 {
    @AfterEach
    public void after()
    {
        container.destroy();
        container = null;
    }

}