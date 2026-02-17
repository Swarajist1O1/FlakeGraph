class DummyClass_94637 {
  @Test public void networkInterceptorReturnsNull() throws Exception {
      @Override public Response intercept(Chain chain) throws IOException {
        chain.proceed(chain.request());
        return null;
      }

}