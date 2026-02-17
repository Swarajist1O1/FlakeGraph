class DummyClass_135737 {
    @Test
    public void testCommandDoesNotRequireContextConcurrencyStrategyProvidesItContextLeftUninitialized() {
        HystrixConcurrencyStrategy strategy = new CustomConcurrencyStrategy(true);
        HystrixPlugins.getInstance().registerConcurrencyStrategy(strategy);

        //context is not set up
        HystrixRequestContext.setContextOnCurrentThread(null);
        HystrixCommand<Boolean> cmd = new TestCommand(false, false);
        assertTrue(cmd.execute()); //command execution not affected by missing context
        printRequestLog();
        assertNull(HystrixRequestLog.getCurrentRequest());
        assertNull(HystrixRequestLog.getCurrentRequest(strategy));
        assertNull(cmd.currentRequestLog);
    }

}