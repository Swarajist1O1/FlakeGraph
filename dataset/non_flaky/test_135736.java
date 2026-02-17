class DummyClass_135736 {
    @Test
    public void testCommandDoesNotRequireContextConcurrencyStrategyProvidesItContextSetUpCorrectly() {
        HystrixConcurrencyStrategy strategy = new CustomConcurrencyStrategy(true);
        HystrixPlugins.getInstance().registerConcurrencyStrategy(strategy);

        //context is set up properly
        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        HystrixCommand<Boolean> cmd = new TestCommand(false, false);
        assertTrue(cmd.execute());
        printRequestLog();
        assertNotNull(HystrixRequestLog.getCurrentRequest());
        assertNotNull(HystrixRequestLog.getCurrentRequest(strategy));
        assertNull(cmd.currentRequestLog);
        context.shutdown();
    }

}