class DummyClass_135732 {
    @Test
    public void testCommandRequiresContextConcurrencyStrategyProvidesItContextSetUpCorrectly() {
        HystrixConcurrencyStrategy strategy = new CustomConcurrencyStrategy(true);
        HystrixPlugins.getInstance().registerConcurrencyStrategy(strategy);

        //context is set up properly
        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        HystrixCommand<Boolean> cmd = new TestCommand(true, true);
        assertTrue(cmd.execute());
        printRequestLog();
        assertNotNull(HystrixRequestLog.getCurrentRequest().getExecutedCommandsAsString());
        assertNotNull(cmd.currentRequestLog);
        context.shutdown();
    }

}