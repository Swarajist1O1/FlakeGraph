class DummyClass_35745 {
  @Test
  public void testChunkRequestSuccess() throws Exception {

    AsyncHttpClientConfig.Builder configBuilder = new AsyncHttpClientConfig.Builder();

    final AsyncHttpClient asyncHttpClient = new AsyncHttpClient(
      new NettyAsyncHttpProvider(configBuilder.build()),
      configBuilder.build());

    byte [] requestBody = generatePostData();
    InetSocketAddress address = ROUTER.getRouterAddress();
    final Request request = new RequestBuilder("POST")
      .setUrl(String.format("http://%s:%d%s", address.getHostName(), address.getPort(), "/v1/upload"))
      .setContentLength(requestBody.length)
      .setBody(new ByteEntityWriter(requestBody))
      .build();

    final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    Future<Void> future = asyncHttpClient.executeRequest(request, new AsyncCompletionHandler<Void>() {
      @Override
      public Void onCompleted(Response response) {
        return null;
      }

}