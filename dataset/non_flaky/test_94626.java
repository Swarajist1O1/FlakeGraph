class DummyClass_94626 {
  @Test public void networkInterceptorsRewriteRequestToServer() throws Exception {
      @Override public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        return chain.proceed(originalRequest.newBuilder()
            .method("POST", uppercase(originalRequest.body()))
            .addHeader("OkHttp-Intercepted", "yep")
            .build());
      }

}