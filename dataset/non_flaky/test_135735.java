class DummyClass_135735 {
    @Test
    public void testCommandRequiresContextConcurrencyStrategyDoesNotProvideItContextLeftUninitialized() {
        HystrixConcurrencyStrategy strategy = new CustomConcurrencyStrategy(false);
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