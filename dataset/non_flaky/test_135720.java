class DummyClass_135720 {
    @Test
    public void testExecutionHookSemaphoreExceptionNoFallback() {
        assertHooksOnFailure(
                new Func0<C>() {
                    @Override
                    public C call() {
                        return getCommand(ExecutionIsolationStrategy.SEMAPHORE, ExecutionResult.FAILURE, FallbackResult.UNIMPLEMENTED);
                    }

}