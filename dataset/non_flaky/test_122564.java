class DummyClass_122564 {
    @Test
    public void addTokensWithMultipleWhiteSpaces() {
        terminal.expectCommand("iptables -L 2>&1");
        commandLine.addTokens("iptables  -L").execute();

        terminal.verifyAllCommandsExecuted();
    }

}