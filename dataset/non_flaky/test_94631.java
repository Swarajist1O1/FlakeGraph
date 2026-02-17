class DummyClass_94631 {
  @Test public void interceptorMakesAnUnrelatedRequest() throws Exception {
          @Override public Response intercept(Chain chain) throws IOException {
            if (chain.request().url().encodedPath().equals("/b")) {
              Request requestA = new Request.Builder()
                  .url(server.url("/a"))
                  .build();
              Response responseA = client.newCall(requestA).execute();
              assertEquals("a", responseA.body().string());
            }

            return chain.proceed(chain.request());
          }

}