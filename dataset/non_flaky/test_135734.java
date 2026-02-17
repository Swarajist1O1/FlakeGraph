class DummyClass_135734 {
    @Test
    public void testCommandRequiresContextConcurrencyStrategyDoesNotProvideItContextSetUpCorrectly() {
        HystrixConcurrencyStrategy strategy = new CustomConcurrencyStrategy(false);
        HystrixPlugins.getInstance().registerConcurrencyStrategy(strategy);

        //context is set up properly
        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        HystrixCommand<Boolean> cmd = new TestCommand(true, true);
        assertTrue(cmd.execute());
        printRequestLog();
        assertNull(HystrixRequestLog.getCurrentRequest());
        assertNull(HystrixRequestLog.getCurrentRequest(strategy));
        assertNull(cmd.currentRequestLog);
        context.shutdown();
    }

}