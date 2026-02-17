class DummyClass_135727 {
    @Test
    public void testExecutionHookSemaphoreShortCircuitSuccessfulFallback() {
        assertHooksOnSuccess(
                new Func0<C>() {
                    @Override
                    public C call() {
                        return getCircuitOpenCommand(ExecutionIsolationStrategy.SEMAPHORE, FallbackResult.SUCCESS);
                    }

}