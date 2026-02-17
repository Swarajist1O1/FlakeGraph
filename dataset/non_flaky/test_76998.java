class DummyClass_76998 {
  @Test
  public void getShuffleAssignmentsTest() throws Exception {
    CoordinatorTestUtils.waitForRegister(coordinatorClient, 3);
    RssGetShuffleAssignmentsRequest request = new RssGetShuffleAssignmentsRequest(
        "app1",
        1,
        1,
        1,
        1,
        Sets.newHashSet(Constants.SHUFFLE_SERVER_VERSION));
    RssGetShuffleAssignmentsResponse response = coordinatorClient.getShuffleAssignments(request);
    assertEquals(1, response.getPartitionToServers().size());
    for (Map.Entry<Integer, List<ShuffleServerInfo>> entry : response.getPartitionToServers().entrySet()) {
      assertEquals(1, entry.getValue().size());
      assertEquals(SHUFFLE_SERVER_PORT + 1, entry.getValue().get(0).getPort());
    }
    request = new RssGetShuffleAssignmentsRequest(
        "app1",
        2,
        1,
        1,
        1,
        Sets.newHashSet(Constants.SHUFFLE_SERVER_VERSION));
    response = coordinatorClient.getShuffleAssignments(request);
    assertEquals(1, response.getPartitionToServers().size());
    for (Map.Entry<Integer, List<ShuffleServerInfo>> entry : response.getPartitionToServers().entrySet()) {
      assertEquals(1, entry.getValue().size());
      assertEquals(SHUFFLE_SERVER_PORT + 1, entry.getValue().get(0).getPort());
    }
    request = new RssGetShuffleAssignmentsRequest(
        "app1",
        2,
        1,
        1,
        1,
        Sets.newHashSet(Constants.SHUFFLE_SERVER_VERSION));
    response = coordinatorClient.getShuffleAssignments(request);
    assertEquals(1, response.getPartitionToServers().size());
    for (Map.Entry<Integer, List<ShuffleServerInfo>> entry : response.getPartitionToServers().entrySet()) {
      assertEquals(1, entry.getValue().size());
      assertEquals(SHUFFLE_SERVER_PORT, entry.getValue().get(0).getPort());
    }
  }

}