class DummyClass_94627 {
  @Test public void networkInterceptorsRewriteResponseFromServer() throws Exception {
      @Override public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());
        return originalResponse.newBuilder()
            .body(uppercase(originalResponse.body()))
            .addHeader("OkHttp-Intercepted", "yep")
            .build();
      }

}