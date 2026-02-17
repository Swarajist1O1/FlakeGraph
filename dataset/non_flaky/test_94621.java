class DummyClass_94621 {
  @Test public void networkInterceptorsCannotCallProceedMultipleTimes() throws Exception {
      @Override public Response intercept(Chain chain) throws IOException {
        chain.proceed(chain.request());
        return chain.proceed(chain.request());
      }

}