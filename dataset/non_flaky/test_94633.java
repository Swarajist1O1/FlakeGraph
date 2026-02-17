class DummyClass_94633 {
  @Test public void networkInterceptorThrowsRuntimeExceptionSynchronous() throws Exception {
      @Override public Response intercept(Chain chain) throws IOException {
        throw new RuntimeException("boom!");
      }

}