class DummyClass_94620 {
  @Test public void networkInterceptorsCannotShortCircuitResponses() throws Exception {
      @Override public Response intercept(Chain chain) throws IOException {
        return new Response.Builder()
            .request(chain.request())
            .protocol(Protocol.HTTP_1_1)
            .code(200)
            .message("Intercepted!")
            .body(ResponseBody.create(MediaType.parse("text/plain; charset=utf-8"), "abc"))
            .build();
      }

}