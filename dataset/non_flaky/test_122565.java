class DummyClass_122565 {
    @Test
    public void addTokensWithSpecialCharacters() {
        terminal.expectCommand("find . ! -name hei 2>&1");
        commandLine.addTokens("find . ! -name hei").execute();

        terminal.verifyAllCommandsExecuted();
    }

}