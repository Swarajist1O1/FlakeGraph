class DummyClass_170484 {
    @AfterEach
    public void tearDown() throws Exception
    {
        if (connectorServer != null)
            connectorServer.stop();
    }

}