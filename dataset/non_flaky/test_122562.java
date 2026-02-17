class DummyClass_122562 {
    @Test
    public void mapException() {
        terminal.ignoreCommand("output");
        CommandResult result = terminal.newCommandLine(context).add("program").execute();
        IllegalArgumentException exception = new IllegalArgumentException("foo");
        try {
            result.mapOutput(output -> { throw exception; });
            fail();
        } catch (UnexpectedOutputException e) {
            assertEquals("Command 'program 2>&1' output was not of the expected format: " +
                    "Failed to map output: stdout/stderr: 'output'", e.getMessage());
            assertTrue(e.getCause() == exception);
        }
    }

}