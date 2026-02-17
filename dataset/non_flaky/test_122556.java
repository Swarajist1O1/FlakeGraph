class DummyClass_122556 {
    @Test
    public void testBasicExecute() {
        terminal.expectCommand("foo bar 2>&1", 0, "line1\nline2\n\n");
        CommandResult result = commandLine.add("foo", "bar").execute();
        assertEquals(0, result.getExitCode());
        assertEquals("line1\nline2", result.getOutput());
        assertEquals("line1\nline2\n\n", result.getUntrimmedOutput());
        assertEquals(List.of("line1", "line2"), result.getOutputLines());
        assertEquals(1, context.getSystemModificationLog().size());
        assertEquals("Executing command: foo bar 2>&1", context.getSystemModificationLog().get(0));

        List<CommandLine> commandLines = terminal.getTestProcessFactory().getMutableCommandLines();
        assertEquals(1, commandLines.size());
        assertTrue(commandLine == commandLines.get(0));

        int lines = result.map(r -> r.getOutputLines().size());
        assertEquals(2, lines);
    }

}