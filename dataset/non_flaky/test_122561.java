class DummyClass_122561 {
    @Test
    public void programFails() {
        terminal.expectCommand("foo 2>&1", 1, "");
        try {
            commandLine.add("foo").execute();
            fail();
        } catch (ChildProcessFailureException e) {
            assertEquals(
                    "Command 'foo 2>&1' terminated with exit code 1: stdout/stderr: ''",
                    e.getMessage());
        }
    }

}