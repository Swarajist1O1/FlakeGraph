class DummyClass_94634 {
  @Test public void networkInterceptorModifiedRequestIsReturned() throws IOException {
      @Override public Response intercept(Chain chain) throws IOException {
        return chain.proceed(chain.request().newBuilder()
            .header("User-Agent", "intercepted request")
            .build());
      }

}