class DummyClass_176857 {
  @Test(expected = IllegalStateException.class)
  public void testLoggingCallableException() {
    new LoggingCallable<Void>() {
      @Override
      public Void doCall() throws IOException {
        throw buildIOE();
      }

}