class DummyClass_94644 {
  @Test public void connectionsAreNotReusedIfNetworkInterceptorInterferes() throws Exception {
      @Override public Response intercept(Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());
        return response.newBuilder()
            .body(ResponseBody.create(null, "unrelated response body!"))
            .build();
      }

}