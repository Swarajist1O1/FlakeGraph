class DummyClass_122559 {
    @Test(expected = NegativeArraySizeException.class)
    public void processFactorySpawnFails() {
        terminal.interceptCommand(
                        commandLine.toString(),
                        command -> { throw new NegativeArraySizeException(); });
        commandLine.add("foo").execute();
    }

}