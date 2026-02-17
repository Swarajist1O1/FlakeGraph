class DummyClass_94629 {
  @Test public void asyncNetworkInterceptors() throws Exception {
      @Override public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());
        return originalResponse.newBuilder()
            .addHeader("OkHttp-Intercepted", "yep")
            .build();
      }

}