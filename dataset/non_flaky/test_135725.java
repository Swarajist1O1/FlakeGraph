class DummyClass_135725 {
    @Test
    public void testExecutionHookSemaphoreRejectedUnsuccessfulFallback() {
        assertHooksOnFailFast(
                new Func0<C>() {
                    @Override
                    public C call() {
                        AbstractCommand.TryableSemaphore semaphore = new AbstractCommand.TryableSemaphoreActual(HystrixProperty.Factory.asProperty(2));

                        final C cmd1 = getLatentCommand(ExecutionIsolationStrategy.SEMAPHORE, ExecutionResult.SUCCESS, 500, FallbackResult.FAILURE, semaphore);
                        final C cmd2 = getLatentCommand(ExecutionIsolationStrategy.SEMAPHORE, ExecutionResult.SUCCESS, 500, FallbackResult.FAILURE, semaphore);

                        //saturate the semaphore
                        new Thread() {
                            @Override
                            public void run() {
                                cmd1.observe();
                            }

}