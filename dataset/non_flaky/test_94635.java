class DummyClass_94635 {
  @Test public void networkInterceptorThrowsRuntimeExceptionAsynchronous() throws Exception {
      @Override public Response intercept(Chain chain) throws IOException {
        throw new RuntimeException("boom!");
      }

}