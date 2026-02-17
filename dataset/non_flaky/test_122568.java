class DummyClass_122568 {
    @Test
    public void testMaxOutputBytes() throws Exception {
        when(commandLine.getTimeout()).thenReturn(Duration.ofSeconds(1));
        when(commandLine.getMaxOutputBytes()).thenReturn(10L);
        when(commandLine.getOutputEncoding()).thenReturn(StandardCharsets.UTF_8);
        when(commandLine.getSigTermGracePeriod()).thenReturn(Duration.ofMinutes(2));
        when(commandLine.getSigKillGracePeriod()).thenReturn(Duration.ofMinutes(3));
        when(commandLine.toString()).thenReturn("program arg");

        when(timer.currentTime()).thenReturn(
                Instant.ofEpochMilli(0),
                Instant.ofEpochMilli(1));

        when(processApi.waitFor(anyLong(), any())).thenReturn(true);

        Files.write(temporaryFile, "1234567890123".getBytes(StandardCharsets.UTF_8));

        try (ChildProcess2Impl child =
                     new ChildProcess2Impl(commandLine, processApi, temporaryFile, timer)) {
            try {
                child.waitForTermination();
                fail();
            } catch (LargeOutputChildProcessException e) {
                assertEquals(
                        "Command 'program arg' output more than 13 bytes: stdout/stderr: '1234567890123'",
                        e.getMessage());
            }
        }
    }

}