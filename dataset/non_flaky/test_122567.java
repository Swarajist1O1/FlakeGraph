class DummyClass_122567 {
    @Test
    public void testTimeout() throws Exception {
        when(commandLine.getTimeout()).thenReturn(Duration.ofSeconds(1));
        when(commandLine.getMaxOutputBytes()).thenReturn(10L);
        when(commandLine.getOutputEncoding()).thenReturn(StandardCharsets.UTF_8);
        when(commandLine.getSigTermGracePeriod()).thenReturn(Duration.ofMinutes(2));
        when(commandLine.getSigKillGracePeriod()).thenReturn(Duration.ofMinutes(3));
        when(commandLine.toString()).thenReturn("program arg");

        when(timer.currentTime()).thenReturn(
                Instant.ofEpochSecond(0),
                Instant.ofEpochSecond(2));

        when(processApi.waitFor(anyLong(), any())).thenReturn(true);

        try (ChildProcess2Impl child =
                     new ChildProcess2Impl(commandLine, processApi, temporaryFile, timer)) {
            try {
                child.waitForTermination();
                fail();
            } catch (TimeoutChildProcessException e) {
                assertEquals(
                        "Command 'program arg' timed out after PT1S: stdout/stderr: ''",
                        e.getMessage());
            }
        }
    }

}