class DummyClass_76997 {
  @Test
  public void rpcMetricsTest() throws Exception{
    String appId = "rpcMetricsTest";
    double oldValue = coordinators.get(0).getGrpcMetrics().getCounterMap()
        .get(CoordinatorGrpcMetrics.HEARTBEAT_METHOD).get();
    CoordinatorTestUtils.waitForRegister(coordinatorClient,2);
    double newValue = coordinators.get(0).getGrpcMetrics().getCounterMap()
        .get(CoordinatorGrpcMetrics.HEARTBEAT_METHOD).get();
    assertTrue(newValue - oldValue > 1);
    assertEquals(0,
        coordinators.get(0).getGrpcMetrics().getGaugeMap()
            .get(CoordinatorGrpcMetrics.HEARTBEAT_METHOD).get(), 0.5);

    RssGetShuffleAssignmentsRequest request = new RssGetShuffleAssignmentsRequest(
        appId, 1, 10, 4, 1,
        Sets.newHashSet(Constants.SHUFFLE_SERVER_VERSION));
    oldValue = coordinators.get(0).getGrpcMetrics().getCounterMap()
        .get(CoordinatorGrpcMetrics.GET_SHUFFLE_ASSIGNMENTS_METHOD).get();
    coordinatorClient.getShuffleAssignments(request);
    newValue = coordinators.get(0).getGrpcMetrics().getCounterMap()
        .get(CoordinatorGrpcMetrics.GET_SHUFFLE_ASSIGNMENTS_METHOD).get();
    assertEquals(oldValue + 1, newValue, 0.5);
    assertEquals(0,
        coordinators.get(0).getGrpcMetrics().getGaugeMap()
            .get(CoordinatorGrpcMetrics.GET_SHUFFLE_ASSIGNMENTS_METHOD).get(), 0.5);
  }

}