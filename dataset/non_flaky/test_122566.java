class DummyClass_122566 {
    @Test
    public void testSuccess() throws Exception {
        when(commandLine.getTimeout()).thenReturn(Duration.ofHours(1));
        when(commandLine.getMaxOutputBytes()).thenReturn(10L);
        when(commandLine.getOutputEncoding()).thenReturn(StandardCharsets.UTF_8);
        when(commandLine.getSigTermGracePeriod()).thenReturn(Duration.ofMinutes(2));
        when(commandLine.getSigKillGracePeriod()).thenReturn(Duration.ofMinutes(3));
        when(commandLine.toString()).thenReturn("program arg");

        when(timer.currentTime()).thenReturn(
                Instant.ofEpochMilli(1),
                Instant.ofEpochMilli(2));

        when(processApi.waitFor(anyLong(), any())).thenReturn(true);

        try (ChildProcess2Impl child =
                     new ChildProcess2Impl(commandLine, processApi, temporaryFile, timer)) {
            child.waitForTermination();
        }
    }

}