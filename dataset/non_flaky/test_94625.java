class DummyClass_94625 {
  @Test public void networkInterceptorsCanChangeRequestMethodFromGetToPost() throws Exception {
      @Override public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, "abc");
        return chain.proceed(originalRequest.newBuilder()
            .method("POST", body)
            .header("Content-Type", mediaType.toString())
            .header("Content-Length", Long.toString(body.contentLength()))
            .build());
      }

}