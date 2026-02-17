class DummyClass_135721 {
    @Test
    public void testExecutionHookSemaphoreExceptionSuccessfulFallback() {
        assertHooksOnSuccess(
                new Func0<C>() {
                    @Override
                    public C call() {
                        return getCommand(ExecutionIsolationStrategy.SEMAPHORE, ExecutionResult.FAILURE, FallbackResult.SUCCESS);
                    }

}