class DummyClass_176855 {
  @Test(expected = IllegalStateException.class)
  public void testLoggingRunnableException() {
    new LoggingRunnable() {
      @Override
      public void doRun() throws IOException {
        throw buildIOE();
      }

}