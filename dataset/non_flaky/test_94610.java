class DummyClass_94610 {
  @Test public void networkInterceptorNotInvokedForFullyCached() throws Exception {
          @Override public Response intercept(Chain chain) throws IOException {
            throw new AssertionError();
          }

}