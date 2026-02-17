class DummyClass_98302 {
  @Test
  public void testAttach() throws Exception {
    when(reader.nextMessage()).thenReturn(
        logMessage(LogMessage.Stream.STDOUT, "hello\n"),
        logMessage(LogMessage.Stream.STDERR, "oops\n"),
        logMessage(LogMessage.Stream.STDOUT, "world!\n"),
        // need to return null to signal end of stream
        null
    );

    final ByteArrayOutputStream stdout = new ByteArrayOutputStream();
    final ByteArrayOutputStream stderr = new ByteArrayOutputStream();
    logStream.attach(stdout, stderr);

    assertThat(stdout.toString(), is("hello\nworld!\n"));
    assertThat(stderr.toString(), is("oops\n"));
  }

}