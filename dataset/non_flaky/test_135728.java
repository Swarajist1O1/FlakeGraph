class DummyClass_135728 {
    @Test
    public void testExecutionHookSemaphoreShortCircuitUnsuccessfulFallback() {
        assertHooksOnFailFast(
                new Func0<C>() {
                    @Override
                    public C call() {
                        return getCircuitOpenCommand(ExecutionIsolationStrategy.SEMAPHORE, FallbackResult.FAILURE);
                    }

}