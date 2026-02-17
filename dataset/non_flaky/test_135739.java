class DummyClass_135739 {
    @Test
    public void testCommandDoesNotRequireContextConcurrencyStrategyDoesNotProvideItContextLeftUninitialized() {
        HystrixConcurrencyStrategy strategy = new CustomConcurrencyStrategy(false);
        HystrixPlugins.getInstance().registerConcurrencyStrategy(strategy);

        //context is not set up
        HystrixRequestContext.setContextOnCurrentThread(null);
        HystrixCommand<Boolean> cmd = new TestCommand(true, true);
        assertTrue(cmd.execute()); //command execution unaffected by missing context
        printRequestLog();
        assertNull(HystrixRequestLog.getCurrentRequest());
        assertNull(HystrixRequestLog.getCurrentRequest(strategy));
        assertNull(cmd.currentRequestLog);
    }

}