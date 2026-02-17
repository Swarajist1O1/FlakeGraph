class DummyClass_122555 {
    @Test
    public void testStrings() {
        terminal.expectCommand(
                "/bin/bash \"with space\" \"speci&l\" \"\" \"double\\\"quote\" 2>&1",
                0,
                "");
        commandLine.add("/bin/bash", "with space", "speci&l", "", "double\"quote").execute();
        assertEquals("bash", commandLine.programName());
    }

}