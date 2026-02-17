class DummyClass_94615 {
  @Test public void interceptorsNotInvoked() throws Exception {
      @Override public Response intercept(Chain chain) throws IOException {
        throw new AssertionError();
      }

}