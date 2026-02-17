class DummyClass_76970 {
  @Test
  public void argEmptyTest() {
    String[] args = new String[0];
    Arguments arguments = new Arguments();
    CommandLine commandLine = new CommandLine(arguments);
    commandLine.parseArgs(args);
    assertNull(arguments.getConfigFile());
  }

}