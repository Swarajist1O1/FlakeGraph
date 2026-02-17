class DummyClass_35751 {
  @Test
  public void testRouterAsync() throws Exception {
    int numElements = 123;
    AsyncHttpClientConfig.Builder configBuilder = new AsyncHttpClientConfig.Builder();

    final AsyncHttpClient asyncHttpClient = new AsyncHttpClient(
      new NettyAsyncHttpProvider(configBuilder.build()),
      configBuilder.build());

    final CountDownLatch latch = new CountDownLatch(numElements);
    final AtomicInteger numSuccessfulRequests = new AtomicInteger(0);
    for (int i = 0; i < numElements; ++i) {
      final int elem = i;
      final Request request = new RequestBuilder("GET")
        .setUrl(resolveURI(String.format("%s/%s-%d", "/v1/echo", "async", i)))
        .build();
      asyncHttpClient.executeRequest(request, new AsyncCompletionHandler<Void>() {
        @Override
        public Void onCompleted(Response response) throws Exception {
          latch.countDown();
          Assert.assertEquals(HttpResponseStatus.OK.code(), response.getStatusCode());
          String responseBody = response.getResponseBody();
          LOG.trace("Got response {}", responseBody);
          Assert.assertEquals("async-" + elem, responseBody);
          numSuccessfulRequests.incrementAndGet();
          return null;
        }

}