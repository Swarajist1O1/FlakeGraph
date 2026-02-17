class DummyClass_176856 {
  @Test
  public void testLoggingCallable() {
    Integer result = new LoggingCallable<Integer>() {
      @Override
      public Integer doCall() {
        return 3;
      }

}