class DummyClass_122557 {
    @Test
    public void verifyDefaults() {
        assertEquals(CommandLine.DEFAULT_TIMEOUT, commandLine.getTimeout());
        assertEquals(CommandLine.DEFAULT_MAX_OUTPUT_BYTES, commandLine.getMaxOutputBytes());
        assertEquals(CommandLine.DEFAULT_SIGTERM_GRACE_PERIOD, commandLine.getSigTermGracePeriod());
        assertEquals(CommandLine.DEFAULT_SIGKILL_GRACE_PERIOD, commandLine.getSigKillGracePeriod());
        assertEquals(0, commandLine.getArguments().size());
        assertEquals(Optional.empty(), commandLine.getOutputFile());
        assertEquals(StandardCharsets.UTF_8, commandLine.getOutputEncoding());
        assertTrue(commandLine.getRedirectStderrToStdoutInsteadOfDiscard());
        Predicate<Integer> defaultExitCodePredicate = commandLine.getSuccessfulExitCodePredicate();
        assertTrue(defaultExitCodePredicate.test(0));
        assertFalse(defaultExitCodePredicate.test(1));
    }

}