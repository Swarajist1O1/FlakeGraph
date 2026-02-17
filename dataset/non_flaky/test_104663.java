class DummyClass_104663 {
  @Test
  public void testGrpcQueryServer()
      throws Exception {
    GrpcQueryClient queryClient = new GrpcQueryClient("localhost", CommonConstants.Server.DEFAULT_GRPC_PORT);
    String sql = "SELECT * FROM mytable_OFFLINE LIMIT 1000000";
    BrokerRequest brokerRequest = new Pql2Compiler().compileToBrokerRequest(sql);
    List<String> segments = _helixResourceManager.getSegmentsFor("mytable_OFFLINE");

    GrpcRequestBuilder requestBuilder = new GrpcRequestBuilder().setSegments(segments);
    testNonStreamingRequest(queryClient.submit(requestBuilder.setSql(sql).build()));
    testNonStreamingRequest(queryClient.submit(requestBuilder.setBrokerRequest(brokerRequest).build()));

    requestBuilder.setEnableStreaming(true);
    testStreamingRequest(queryClient.submit(requestBuilder.setSql(sql).build()));
    testStreamingRequest(queryClient.submit(requestBuilder.setBrokerRequest(brokerRequest).build()));
  }

}