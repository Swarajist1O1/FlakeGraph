class DummyClass_94609 {
  @Test public void networkInterceptorInvokedForConditionalGet() throws Exception {
          @Override public Response intercept(Chain chain) throws IOException {
            ifNoneMatch.compareAndSet(null, chain.request().header("If-None-Match"));
            return chain.proceed(chain.request());
          }

}