class DummyClass_122569 {
    @Test
    public void testUnkillable() throws Exception {
        when(commandLine.getTimeout()).thenReturn(Duration.ofSeconds(1));
        when(commandLine.getMaxOutputBytes()).thenReturn(10L);
        when(commandLine.getOutputEncoding()).thenReturn(StandardCharsets.UTF_8);
        when(commandLine.getSigTermGracePeriod()).thenReturn(Duration.ofMinutes(2));
        when(commandLine.getSigKillGracePeriod()).thenReturn(Duration.ofMinutes(3));
        when(commandLine.toString()).thenReturn("program arg");

        when(timer.currentTime()).thenReturn(
                Instant.ofEpochMilli(0),
                Instant.ofEpochMilli(1));

        when(processApi.waitFor(anyLong(), any())).thenReturn(false);

        Files.write(temporaryFile, "1234567890123".getBytes(StandardCharsets.UTF_8));

        try (ChildProcess2Impl child =
                     new ChildProcess2Impl(commandLine, processApi, temporaryFile, timer)) {
            try {
                child.waitForTermination();
                fail();
            } catch (UnkillableChildProcessException e) {
                assertEquals(
                        "Command 'program arg' did not terminate even after SIGTERM, +PT2M, SIGKILL, and +PT3M: stdout/stderr: '1234567890123'",
                        e.getMessage());
            }
        }
    }

}