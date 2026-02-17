class DummyClass_135733 {
    @Test
    public void testCommandRequiresContextConcurrencyStrategyProvidesItContextLeftUninitialized() {
        HystrixConcurrencyStrategy strategy = new CustomConcurrencyStrategy(true);
        HystrixPlugins.getInstance().registerConcurrencyStrategy(strategy);

        //context is not set up
        HystrixRequestContext.setContextOnCurrentThread(null);
        HystrixCommand<Boolean> cmd = new TestCommand(true, true);
        assertTrue(cmd.execute()); //command execution not affected by missing context
        printRequestLog();
        assertNull(HystrixRequestLog.getCurrentRequest());
        assertNull(HystrixRequestLog.getCurrentRequest(strategy));
        assertNull(cmd.currentRequestLog);
    }

}