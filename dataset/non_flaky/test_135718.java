class DummyClass_135718 {
    @Test
    public void testExecutionHookSemaphoreSuccess() {
        assertHooksOnSuccess(
                new Func0<C>() {
                    @Override
                    public C call() {
                        return getCommand(ExecutionIsolationStrategy.SEMAPHORE, ExecutionResult.SUCCESS, FallbackResult.SUCCESS);
                    }

}