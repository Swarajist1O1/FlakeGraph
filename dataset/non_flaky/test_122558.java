class DummyClass_122558 {
    @Test
    public void executeSilently() {
        terminal.ignoreCommand("");
        commandLine.add("foo", "bar").executeSilently();
        assertEquals(0, context.getSystemModificationLog().size());
        commandLine.recordSilentExecutionAsSystemModification();
        assertEquals(1, context.getSystemModificationLog().size());
        assertEquals("Executed command: foo bar 2>&1", context.getSystemModificationLog().get(0));
    }

}