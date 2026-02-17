class DummyClass_94632 {
  @Test public void interceptorMakesAnUnrelatedAsyncRequest() throws Exception {
          @Override public Response intercept(Chain chain) throws IOException {
            if (chain.request().url().encodedPath().equals("/b")) {
              Request requestA = new Request.Builder()
                  .url(server.url("/a"))
                  .build();

              try {
                RecordingCallback callbackA = new RecordingCallback();
                client.newCall(requestA).enqueue(callbackA);
                callbackA.await(requestA.url()).assertBody("a");
              } catch (Exception e) {
                throw new RuntimeException(e);
              }
            }

            return chain.proceed(chain.request());
          }

}