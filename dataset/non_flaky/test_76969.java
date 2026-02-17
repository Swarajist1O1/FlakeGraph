class DummyClass_76969 {
  @Test
  public void argTest() {
    String[] args = {"-c", confFile};
    Arguments arguments = new Arguments();
    CommandLine commandLine = new CommandLine(arguments);
    commandLine.parseArgs(args);
    assertEquals(confFile, arguments.getConfigFile());
  }

}