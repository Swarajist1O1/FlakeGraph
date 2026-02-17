class DummyClass_177224 {
    @Test
    public void testRestartableThreadRestartBehavior() {
        final RestartableThread restartableThread =
                new RestartableThread(testName.getMethodName(), () -> () -> {
                    while (!Thread.currentThread().isInterrupted()) {
                        Thread.yield();
                    }
                });

        restartableThread.start();
        assertThat(restartableThread.isRunning()).isTrue();
        restartableThread.stop();
        assertThat(restartableThread.isRunning()).isFalse();
        restartableThread.start();
        assertThat(restartableThread.isRunning()).isTrue();
        restartableThread.stop();
        assertThat(restartableThread.isRunning()).isFalse();
    }

}