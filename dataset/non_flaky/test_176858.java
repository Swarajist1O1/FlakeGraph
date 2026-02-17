class DummyClass_176858 {
  @Test(expected = IllegalStateException.class)
  public void testLoggingVoidCallableException() {
    new LoggingVoidCallable() {
      @Override
      public void doCall() throws IOException {
        throw buildIOE();
      }

}