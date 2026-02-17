class DummyClass_135722 {
    @Test
    public void testExecutionHookSemaphoreExceptionUnsuccessfulFallback() {
        assertHooksOnFailure(
                new Func0<C>() {
                    @Override
                    public C call() {
                        return getCommand(ExecutionIsolationStrategy.SEMAPHORE, ExecutionResult.FAILURE, FallbackResult.FAILURE);
                    }

}