class DummyClass_135755 {
    @Test
    public void testNoRequestContextOnSimpleConcurencyStrategyWithoutException() throws Exception {
        shutdownContextIfExists();
        ConfigurationManager.getConfigInstance().setProperty("hystrix.command.default.requestLog.enabled", "false");

        new SimpleCommand().execute();

        assertTrue("We are able to run the simple command without a context initialization error.", true);
    }

}