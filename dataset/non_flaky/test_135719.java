class DummyClass_135719 {
    @Test
    public void testExecutionHookSemaphoreBadRequestException() {
        assertHooksOnFailure(
                new Func0<C>() {
                    @Override
                    public C call() {
                        return getCommand(ExecutionIsolationStrategy.SEMAPHORE, ExecutionResult.BAD_REQUEST, FallbackResult.SUCCESS);
                    }

}